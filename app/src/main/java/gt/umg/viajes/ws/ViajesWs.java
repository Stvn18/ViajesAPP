package gt.umg.viajes.ws;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gt.umg.viajes.common.Common;
import gt.umg.viajes.dto.CustomResponseEntityDto;
import gt.umg.viajes.entities.User;
import gt.umg.viajes.entities.UserSession;

/**
 * Created by wilver on 13/04/17.
 */

public class ViajesWs {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public ViajesWs(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);

        MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJacksonHttpMessageConverter();

        mappingJacksonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        mappingJacksonHttpMessageConverter.getObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new StringHttpMessageConverter());
        converters.add(mappingJacksonHttpMessageConverter);

        restTemplate.setMessageConverters(converters);

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public CustomResponseEntityDto<UserSession> login(String email, String password){
        CustomResponseEntityDto<UserSession> customResponseEntity = new CustomResponseEntityDto<>();
        try{

            Map<String, String> parameters = new HashMap<>();
            parameters.put("email", email);
            parameters.put("password", password);

            ResponseEntity<UserSession> responseEntity = restTemplate.postForEntity(Common.getUrlWs() + "UserSession/login?email={email}&password={password}", null, UserSession.class, parameters);

            customResponseEntity.setResponseCode(201);
            customResponseEntity.setResponseData(responseEntity.getBody());

            return customResponseEntity;
        }catch(HttpClientErrorException exception){
            customResponseEntity.setResponseCode(Integer.parseInt(exception.getStatusCode().toString()));

            return customResponseEntity;
        } catch (ResourceAccessException resourceAccessException){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(resourceAccessException.getMessage());

            return customResponseEntity;
        } catch (RestClientException restClientException){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(restClientException.getMessage());

            return customResponseEntity;
        } catch (Exception exception){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(exception.getMessage());

            return customResponseEntity;
        }
    }

    public CustomResponseEntityDto<User> signUp(User user){
        CustomResponseEntityDto<User> customResponseEntity = new CustomResponseEntityDto<>();
        try{

            Map<String, String> parameters = new HashMap<>();

            ResponseEntity<User> responseEntity = restTemplate.postForEntity(Common.getUrlWs() + "User/signUp", user, User.class, parameters);

            customResponseEntity.setResponseCode(201);
            customResponseEntity.setResponseData(responseEntity.getBody());

            return customResponseEntity;

        }catch(HttpClientErrorException exception){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(exception.getMessage());
            return customResponseEntity;
        } catch (ResourceAccessException resourceAccessException){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(resourceAccessException.getMessage());

            return customResponseEntity;
        } catch (RestClientException restClientException){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(restClientException.getMessage());

            return customResponseEntity;
        }
    }

}
