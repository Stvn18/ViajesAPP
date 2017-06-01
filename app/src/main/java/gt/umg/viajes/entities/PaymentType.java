package gt.umg.viajes.entities;

/**
 * Created by wilver on 31/05/17.
 */

public enum PaymentType {

    VISA(1, "Visa"), MASTERCARD(2, "Mastercard"), AMERICAN_EXPRESS(3, "American express"), VISA_ELECTRON(4, "Visa electron");

    private final int id;
    private final String description;

    private PaymentType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
