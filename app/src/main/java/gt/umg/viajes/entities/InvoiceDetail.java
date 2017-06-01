package gt.umg.viajes.entities;


/**
 * Created by wilver on 31/05/17.
 */

public class InvoiceDetail extends GenericEntity {

    private static final long serialVersionUID = -5216497945705017438L;

    private Integer quantity;
    private Float unitPrice;
    private Float subTotal;
    private Product product;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Integer quantity, Float unitPrice, Float subTotal, Product product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
