package gt.umg.viajes.ws;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilver on 17/05/17.
 */

public class WebServiceRestConfig {
    protected RestTemplate restTemplate = new RestTemplate();
    protected ObjectMapper objectMapper = new ObjectMapper();

    public WebServiceRestConfig() {
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
}
