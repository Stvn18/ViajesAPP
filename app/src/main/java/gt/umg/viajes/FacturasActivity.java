package gt.umg.viajes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import gt.umg.viajes.adapters.FacturasAdapter;
import gt.umg.viajes.entities.Invoice;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

public class FacturasActivity extends AppCompatActivity {

    private ListView listView;
    private ViajesWs viajesWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);

        listView = (ListView) findViewById(R.id.facturas_activity_list_view);
        viajesWs = new ViajesWs();

        viajesWs.findInvoiceByUser().execute(new ResourceResponse<Invoice[]>() {
            @Override
            public void success(int statusCode, Invoice[] responseData) {

                FacturasAdapter adapter = new FacturasAdapter(FacturasActivity.this, responseData);
                listView.setAdapter(adapter);

            }

            @Override
            public void error(int errorCode, String error) {

            }
        });



    }
}
