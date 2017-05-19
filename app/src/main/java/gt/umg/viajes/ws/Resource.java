package gt.umg.viajes.ws;

import android.os.Handler;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.Map;

/**
 * Created by wilver on 17/05/17.
 */

public class Resource<T> extends WebServiceRestConfig {

    private ResourceResponse<T> resourceResponse = null;
    private Map<String, String> parameters = null;
    private String url = null;
    private Class<T> responseType = null;
    private Object body;
    private Handler handler = new Handler();

    private boolean hasExceptions = false;
    private String error = null;
    private int errorCode = 0;
    private T responseData;
    private int statusCode = 0;

    private HttpMethod httpMethod = null;

    public Resource setUrl(String url) {
        this.url = url;
        return this;
    }

    public Resource get(String url, Map<String, String> parameters, Class<T> responseType) {
        this.httpMethod = HttpMethod.GET;
        this.url = url;
        this.parameters = parameters;
        this.responseType = responseType;
        return this;
    }

    public Resource post(String url, Map<String, String> parameters, Class<T> responseType, Object body){
        this.httpMethod = HttpMethod.POST;
        this.url = url;
        this.parameters = parameters;
        this.responseType = responseType;
        this.body = body;
        return this;
    }

    public void execute(ResourceResponse<T> resourceResponse) {
        this.resourceResponse = resourceResponse;
        executeThread();
    }

    private void executeThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ResponseEntity<T> responseEntity = null;

                    switch (httpMethod) {
                        case GET:
                            responseEntity = restTemplate.getForEntity(url, responseType, parameters);
                            break;
                        case POST:
                            responseEntity = restTemplate.postForEntity(url, body, responseType, parameters);
                        default:
                            break;
                    }

                    statusCode = Integer.parseInt(responseEntity.getStatusCode().toString());
                    responseData = responseEntity.getBody();
                } catch (final HttpClientErrorException httpClientErrorException) {
                    hasExceptions = true;
                    errorCode = Integer.parseInt(httpClientErrorException.getStatusCode().toString());
                    error = httpClientErrorException.getMessage();
                } catch (final ResourceAccessException resourceAccessException) {
                    hasExceptions = true;
                    error = resourceAccessException.getMessage();
                } catch (RestClientException restClientException) {
                    hasExceptions = true;
                    error = restClientException.getMessage();
                } catch (Exception exception) {
                    hasExceptions = true;
                    error = exception.getMessage();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (hasExceptions) {
                            resourceResponse.error(errorCode, error);
                        } else {
                            resourceResponse.success(statusCode, responseData);
                        }
                    }
                });
            }
        }).start();
    }
}
