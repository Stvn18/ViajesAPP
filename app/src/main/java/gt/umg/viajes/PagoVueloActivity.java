package gt.umg.viajes;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gt.umg.viajes.adapters.TipoPagoAdapter;
import gt.umg.viajes.common.Common;
import gt.umg.viajes.common.Utils;
import gt.umg.viajes.entities.Invoice;
import gt.umg.viajes.entities.InvoiceDetail;
import gt.umg.viajes.entities.PaymentType;
import gt.umg.viajes.entities.Product;
import gt.umg.viajes.entities.ProductType;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

public class PagoVueloActivity extends AppCompatActivity {

    private String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String[] year = {"17", "18", "19", "20", "21", "22"};
    private PaymentType[] tipoPagoArray = {PaymentType.VISA, PaymentType.MASTERCARD, PaymentType.AMERICAN_EXPRESS, PaymentType.VISA_ELECTRON};

    private Spinner mesCaducidad;
    private Spinner yearCaducidad;
    private Spinner tipoPagoSpinner;
    private Button pagarButton;
    private EditText tarjetaNombreEditText;
    private EditText numeroTarjetaEditText;
    private EditText codigoSeguridadEditText;
    private Bundle parameters;
    private ViajesWs viajesWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_vuelo);

        setTitle("Datos de pago");

        parameters = getIntent().getExtras();
        viajesWs = new ViajesWs();

        mesCaducidad = (Spinner) findViewById(R.id.pago_vuelo_mes_caducidad);
        yearCaducidad = (Spinner) findViewById(R.id.pago_vuelo_year_caducidad);
        tipoPagoSpinner = (Spinner) findViewById(R.id.pago_vuelo_tipo_pago);
        pagarButton = (Button) findViewById(R.id.pago_vuelo_confirmar_button);
        tarjetaNombreEditText = (EditText) findViewById(R.id.pago_vuelo_tarjeta_nombre);
        numeroTarjetaEditText = (EditText) findViewById(R.id.pago_vuelo_numero_tarjeta);
        codigoSeguridadEditText = (EditText) findViewById(R.id.pago_vuelo_tarjeta_codigo_seguridad);

        ArrayAdapter<String> adapterMeses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        ArrayAdapter<String> adapterYears = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year);

        mesCaducidad.setAdapter(adapterMeses);
        yearCaducidad.setAdapter(adapterYears);

        TipoPagoAdapter tipoPagoAdapter = new TipoPagoAdapter(tipoPagoArray, this);
        tipoPagoSpinner.setAdapter(tipoPagoAdapter);

        pagarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagar();
            }
        });
    }

    private void pagar(){

        if(tarjetaNombreEditText.getText() == null || "".equals(tarjetaNombreEditText.getText().toString())){
            tarjetaNombreEditText.setError("Informacion requerida");
            return;
        }

        if(numeroTarjetaEditText.getText() == null || "".equals(numeroTarjetaEditText.getText().toString())){
            numeroTarjetaEditText.setError("Informacion requerida");
            return;
        }

        if(codigoSeguridadEditText.getText() == null || "".equals(codigoSeguridadEditText.getText().toString())){
            codigoSeguridadEditText.setError("Informacion requerida");
            return;
        }

        Product product = new Product();
        product.setProductType(ProductType.HOTEL);
        product.setFlightTicket(Common.getFlightTicket());
        product.setActive(true);

        Invoice invoice = new Invoice();
        invoice.setActive(true);
        invoice.setCardholderName(tarjetaNombreEditText.getText().toString());
        invoice.setCardNumber(numeroTarjetaEditText.getText().toString());
        invoice.setSecurityCode(codigoSeguridadEditText.getText().toString());
        invoice.setPaymentType((PaymentType) tipoPagoSpinner.getSelectedItem());
        invoice.setTotal(0F);

        List<InvoiceDetail> detail = new ArrayList<>();

        if(parameters.getByte("numeroAdultos") > 0){
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setActive(true);
            invoiceDetail.setProduct(product);
            invoiceDetail.setQuantity(Integer.parseInt(Byte.toString(parameters.getByte("numeroAdultos"))));
            invoiceDetail.setUnitPrice(product.getFlightTicket().getFlight().getAdultPrice());
            invoiceDetail.setSubTotal(invoiceDetail.getUnitPrice() * invoiceDetail.getQuantity());

            detail.add(invoiceDetail);
        }

        if(parameters.getByte("numeroChilds") > 0){
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setActive(true);
            invoiceDetail.setProduct(product);
            invoiceDetail.setQuantity(Integer.parseInt(Byte.toString(parameters.getByte("numeroChilds"))));
            invoiceDetail.setUnitPrice(product.getFlightTicket().getFlight().getChildPrice());
            invoiceDetail.setSubTotal(invoiceDetail.getUnitPrice() * invoiceDetail.getQuantity());

            detail.add(invoiceDetail);
        }

        invoice.setDetail(detail);

        float total = 0F;

        for(InvoiceDetail invoiceDetail : invoice.getDetail()){
            total += invoiceDetail.getSubTotal();
        }

        invoice.setTotal(total);

        Utils.showCustomProgressDialog("Procesando pago...", this);
        viajesWs.createInvoice(invoice).execute(new ResourceResponse<Invoice>() {
            @Override
            public void success(int statusCode, Invoice responseData) {
                Utils.hideCustomProgressDialog();

                Common.setFlightTicket(null);

                PagoVueloActivity.this.finish();
                Toast.makeText(PagoVueloActivity.this, "Pago procesado correctamente!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(int errorCode, String error) {
                Utils.hideCustomProgressDialog();
                Utils.showCustomMessage(1, error, PagoVueloActivity.this);
            }
        });

    }
}
