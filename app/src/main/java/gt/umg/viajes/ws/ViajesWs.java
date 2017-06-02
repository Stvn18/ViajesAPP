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
import gt.umg.viajes.entities.Airline;
import gt.umg.viajes.entities.Flight;
import gt.umg.viajes.entities.FlightPreferredClass;
import gt.umg.viajes.entities.HotelDetail;
import gt.umg.viajes.entities.Invoice;
import gt.umg.viajes.entities.Location;
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
        }catch (Exception exception){
            customResponseEntity.setResponseCode(0);
            customResponseEntity.setResponseMessage(exception.getMessage());

            return customResponseEntity;
        }
    }

    public Resource<Location[]> getLocationArray(){
        Map<String, String> parameters = new HashMap<>();
        return new Resource<Location[]>().get(Common.getUrlWs() + "Location", parameters, Location[].class);
    }

    public Resource<HotelDetail[]> getHotelServices(String locationId, String childrens, String adults, String bedrooms, Long lDateIn, Long lDateOut){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("locationId", locationId);
        parameters.put("childrens", childrens);
        parameters.put("adults", adults);
        parameters.put("bedrooms", bedrooms);
        parameters.put("lDateIn", lDateIn.toString());
        parameters.put("lDateOut", lDateOut.toString());
        return new Resource<HotelDetail[]>().get(Common.getUrlWs() + "Hotel/findServices?locationId={locationId}&childrens={childrens}&adults={adults}&bedrooms={bedrooms}&lDateIn={lDateIn}&lDateOut={lDateOut}", parameters, HotelDetail[].class);
    }

    public Resource<FlightPreferredClass[]> getPreferredClass(){
        String url = Common.getUrlWs() + "FlightPreferredClass";
        Map<String, String> parameters = new HashMap<>();
        return new Resource<FlightPreferredClass[]>().get(url, parameters, FlightPreferredClass[].class);
    }

    public Resource<Airline[]> getAirlines(){
        String url = Common.getUrlWs() + "Airline";
        Map<String, String> parameters = new HashMap<>();
        return new Resource<Airline[]>().get(url, parameters, Airline[].class);
    }

    public Resource<Flight[]> getVuelos(Integer flyingFromId, Integer flyingToId, Integer preferredClassId, Integer airlineId){
        String url = Common.getUrlWs() + "Flight?flyingFromId={flyingFromId}&flyingToId={flyingToId}&preferredClassId={preferredClassId}&airlineId={airlineId}";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("flyingFromId", flyingFromId.toString());
        parameters.put("flyingToId", flyingToId.toString());
        parameters.put("preferredClassId", preferredClassId.toString());
        parameters.put("airlineId", airlineId.toString());
        return new Resource<Flight[]>().get(url, parameters, Flight[].class);
    }

    public Resource<Invoice> createInvoice(Invoice invoice){
        String url = Common.getUrlWs() + "Invoice?token={token}";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", Common.getSession().getToken());
        return new Resource<Invoice>().post(url, parameters, Invoice.class, invoice);
    }

    public Resource<Invoice[]> findInvoiceByUser(){
        String url = Common.getUrlWs() + "Invoice/findByUser?token={token}";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", Common.getSession().getToken());
        return new Resource<Invoice[]>().get(url, parameters, Invoice[].class);
    }

}
