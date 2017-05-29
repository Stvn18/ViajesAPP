package gt.umg.viajes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import gt.umg.viajes.adapters.VuelosAdapter;
import gt.umg.viajes.entities.Flight;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

public class VueloDetalleActivity extends Activity {

    ViajesWs viajesWs;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo_detalle);
        viajesWs = new ViajesWs();
        listView = (ListView) findViewById(R.id.vuelo_detalle_list_view);

        Bundle parameters = getIntent().getExtras();

        Log.d("getVuelos", Long.toString(parameters.getLong("lFechaSalida")));
        Log.d("getVuelos", Long.toString(parameters.getLong("lFechaRegreso")));

        viajesWs.getVuelos(
                parameters.getInt("origenId"),
                parameters.getInt("destinoId"),
                parameters.getLong("lFechaSalida"),
                parameters.getLong("lFechaRegreso"),
                parameters.getByte("numeroAdultos"),
                parameters.getByte("numeroChilds"),
                parameters.getInt("classId"),
                parameters.getInt("airlineId")
        ).execute(new ResourceResponse<Flight[]>() {
            @Override
            public void success(int statusCode, Flight[] responseData) {

                VuelosAdapter adapter = new VuelosAdapter(VueloDetalleActivity.this, responseData);
                listView.setAdapter(adapter);

            }

            @Override
            public void error(int errorCode, String error) {
                Log.e("getVuelos", error);
            }
        });

    }
}
