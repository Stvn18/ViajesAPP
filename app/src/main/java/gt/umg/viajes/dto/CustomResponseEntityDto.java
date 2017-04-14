package gt.umg.viajes.dto;

/**
 * Created by wilver on 13/04/17.
 */

public class CustomResponseEntityDto<T> {

    private Integer responseCode;
    private String responseMessage;
    private T responseData;

    public CustomResponseEntityDto() {
    }

    public CustomResponseEntityDto(Integer responseCode, String responseMessage, T responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

}
