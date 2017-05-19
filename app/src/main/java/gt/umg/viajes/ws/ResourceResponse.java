package gt.umg.viajes.ws;

/**
 * Created by wilver on 17/05/17.
 */

public interface ResourceResponse<T> {

    public void success(int statusCode, T responseData);

    public void error(int errorCode, String error);
}