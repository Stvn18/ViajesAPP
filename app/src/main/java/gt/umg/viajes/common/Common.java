package gt.umg.viajes.common;

import gt.umg.viajes.dto.SessionDto;

/**
 * Created by wilver on 13/04/17.
 */

public class Common {

    private static String urlWs;
    private static SessionDto session;

    public static void setUrlWs(String urlWs){
        Common.urlWs = urlWs;
    }

    public static String getUrlWs(){
        return Common.urlWs;
    }

    public static SessionDto getSession() {
        return Common.session;
    }

    public static void setSession(SessionDto session) {
        Common.session = session;
    }
}
