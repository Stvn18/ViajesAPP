package gt.umg.viajes.dto;

/**
 * Created by wilver on 15/04/17.
 */

public class SessionDto {

    private String name;
    private String email;
    private String token;
    private boolean helpActive;

    public SessionDto() {
    }

    public SessionDto(String name, String email, String token, boolean helpActive) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.helpActive = helpActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isHelpActive() {
        return helpActive;
    }

    public void setHelpActive(boolean helpActive) {
        this.helpActive = helpActive;
    }
}
