package gt.umg.viajes.entities;

import java.util.Date;

/**
 * Created by wilver on 13/04/17.
 */

public class UserSession {

    private static final long serialVersionUID = -8986545581442171557L;

    private Integer id;

    private String token;

    private Date startDate;

    private Date endDate;

    private User user;

    public UserSession() {
    }

    public UserSession(Integer id, String token, Date startDate, Date endDate, User user) {
        this.id = id;
        this.token = token;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
