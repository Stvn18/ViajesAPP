package gt.umg.viajes.entities;

import java.util.List;

/**
 * Created by wilver on 31/05/17.
 */

public class Invoice extends GenericEntity {

    private static final long serialVersionUID = 23256028518132415L;

    private User user;
    private Float total;
    private PaymentType paymentType;
    private String cardNumber;
    private String cardholderName;
    private String securityCode;
    private List<InvoiceDetail> detail;

    public Invoice() {
    }

    public Invoice(User user, Float total, PaymentType paymentType, String cardNumber, String cardholderName, String securityCode) {
        this.user = user;
        this.total = total;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.securityCode = securityCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public List<InvoiceDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<InvoiceDetail> detail) {
        this.detail = detail;
    }

}
