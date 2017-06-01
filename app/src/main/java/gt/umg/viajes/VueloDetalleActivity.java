package gt.umg.viajes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import gt.umg.viajes.adapters.VuelosAdapter;
import gt.umg.viajes.common.Common;
import gt.umg.viajes.entities.Flight;
import gt.umg.viajes.entities.FlightTicket;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

public class VueloDetalleActivity extends Activity {

    ViajesWs viajesWs;

    private ListView listView;
    private VuelosAdapter adapter;
    private Bundle parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo_detalle);
        viajesWs = new ViajesWs();
        listView = (ListView) findViewById(R.id.vuelo_detalle_list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                FlightTicket flightTicket = new FlightTicket();
                flightTicket.setFlight(adapter.getItem(position));
                flightTicket.setDateFlight(new Date(parameters.getLong("lFechaSalida")));
                flightTicket.setActive(true);

                Common.setFlightTicket(flightTicket);
                Intent intent = new Intent(VueloDetalleActivity.this, PagoVueloActivity.class);
                intent.putExtra("numeroAdultos", parameters.getByte("numeroAdultos"));
                intent.putExtra("numeroChilds", parameters.getByte("numeroChilds"));

                startActivity(intent);
                VueloDetalleActivity.this.finish();

            }
        });

        parameters = getIntent().getExtras();

        viajesWs.getVuelos(
                parameters.getInt("origenId"),
                parameters.getInt("destinoId"),
                parameters.getInt("classId"),
                parameters.getInt("airlineId")
        ).execute(new ResourceResponse<Flight[]>() {
            @Override
            public void success(int statusCode, Flight[] responseData) {

                adapter = new VuelosAdapter(VueloDetalleActivity.this, responseData);
                listView.setAdapter(adapter);

            }
            @Override
            public void error(int errorCode, String error) {
                Log.e("getVuelos", error);
            }
        });

    }
}
