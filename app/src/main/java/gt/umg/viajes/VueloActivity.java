package gt.umg.viajes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import gt.umg.viajes.adapters.AirlineAdapter;
import gt.umg.viajes.adapters.LocationAdapter;
import gt.umg.viajes.adapters.PreferredClassAdapter;
import gt.umg.viajes.common.Common;
import gt.umg.viajes.common.Utils;
import gt.umg.viajes.entities.Airline;
import gt.umg.viajes.entities.FlightPreferredClass;
import gt.umg.viajes.entities.Location;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

import static gt.umg.viajes.R.id.vuelo_buscar_button;

public class VueloActivity extends AppCompatActivity {

    private Button buscarButton;
    private Button vueloFechaSalidaButton;
    private Button vueloFechaRegresoButton;
    private EditText vueloFechaSalidaEditText;
    private EditText vueloFechaRegresoEditText;
    private Spinner vueloOrigenSpinner;
    private Spinner vueloDestinoSpinner;
    private Spinner vueloAirlineSpinner;
    private Spinner vueloPreferredClassSpinner;
    private EditText vueloNumeroAdultosEditText;
    private EditText vueloNumeroChildsEditText;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int year;
    private int month;
    private int day;

    private ViajesWs viajesWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo);

        setTitle("Busque su vuelo");

        buscarButton = (Button) findViewById(vuelo_buscar_button);
        vueloFechaSalidaButton = (Button) findViewById(R.id.vuelo_fecha_salida_button);
        vueloFechaRegresoButton = (Button) findViewById(R.id.vuelo_fecha_regreso_button);
        vueloFechaSalidaEditText = (EditText) findViewById(R.id.vuelo_fecha_salida);
        vueloFechaRegresoEditText = (EditText) findViewById(R.id.vuelo_fecha_regreso);
        vueloOrigenSpinner = (Spinner) findViewById(R.id.vuelo_origen_spinner);
        vueloDestinoSpinner = (Spinner) findViewById(R.id.vuelo_destino_spinner);
        vueloAirlineSpinner = (Spinner) findViewById(R.id.vuelo_aereolinea_spinner);
        vueloPreferredClassSpinner = (Spinner) findViewById(R.id.vuelo_clase_spinner);
        vueloNumeroAdultosEditText = (EditText) findViewById(R.id.vuelo_numero_adultos);
        vueloNumeroChildsEditText = (EditText) findViewById(R.id.vuelo_numero_childs);

        vueloFechaSalidaEditText.setEnabled(false);
        vueloFechaRegresoEditText.setEnabled(false);

        viajesWs = new ViajesWs();

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarVuelos();
            }
        });

        vueloFechaSalidaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        vueloFechaRegresoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(2);
            }
        });

        viajesWs.getLocationArray().execute(new ResourceResponse<Location[]>() {
            @Override
            public void success(int statusCode, Location[] responseData) {
                LocationAdapter locationOrigenAdapter = new LocationAdapter(responseData, VueloActivity.this);
                LocationAdapter locationDestinoAdapter = new LocationAdapter(responseData, VueloActivity.this);

                vueloOrigenSpinner.setAdapter(locationOrigenAdapter);
                vueloDestinoSpinner.setAdapter(locationDestinoAdapter);
            }

            @Override
            public void error(int errorCode, String error) {
                Log.e("getLocationArray", error);
            }
        });

        viajesWs.getAirlines().execute(new ResourceResponse<Airline[]>() {
            @Override
            public void success(int statusCode, Airline[] responseData) {
                AirlineAdapter adapter = new AirlineAdapter(responseData, VueloActivity.this);
                vueloAirlineSpinner.setAdapter(adapter);
            }

            @Override
            public void error(int errorCode, String error) {
                Log.e("getAirlines", error);
            }
        });

        viajesWs.getPreferredClass().execute(new ResourceResponse<FlightPreferredClass[]>() {
            @Override
            public void success(int statusCode, FlightPreferredClass[] responseData) {
                PreferredClassAdapter adapter = new PreferredClassAdapter(responseData, VueloActivity.this);
                vueloPreferredClassSpinner.setAdapter(adapter);
            }

            @Override
            public void error(int errorCode, String error) {
                Log.e("getPreferredClass", error);
            }
        });

        setCurrentDate();
    }

    private DatePickerDialog.OnDateSetListener dateSetListenerInicio = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int yearOf, int monthOfYear,
                              int dayOfMonth) {
            year = yearOf;
            month = monthOfYear;
            day = dayOfMonth;
            updateStartDate();// Show the date on the TextView
        }
    };

    private DatePickerDialog.OnDateSetListener dateSetListenerFin = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int yearOf, int monthOfYear,
                              int dayOfMonth) {
            year = yearOf;
            month = monthOfYear;
            day = dayOfMonth;
            updateEndDate();// Show the date on the TextView
        }
    };

    @Override
    protected Dialog onCreateDialog(final int id) {
        if(id == 1) {
            return new DatePickerDialog(this, dateSetListenerInicio, year, month, day);
        } else {
            return new DatePickerDialog(this, dateSetListenerFin, year, month, day);
        }
    }

    private void setCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        updateStartDate();
        updateEndDate();
    }



    private void updateStartDate(){
        try{

            StringBuilder fecha = new StringBuilder()
                    .append(Utils.padLeft(Integer.toString(day), 2))
                    .append("/")
                    .append(Utils.padLeft(Integer.toString(month + 1), 2))
                    .append("/")
                    .append(year);

            vueloFechaSalidaEditText.setText(fecha.toString());

            Date startDate = simpleDateFormat.parse(fecha.toString());
            Date endDate = simpleDateFormat.parse(vueloFechaRegresoEditText.getText().toString());

            if(startDate.getTime() > endDate.getTime()){
                updateEndDate();
            }

        }catch (Exception exception){
            Log.e("updateStartDate", exception.getMessage());
        }
    }

    private void updateEndDate(){
        try{
            StringBuilder fecha = new StringBuilder()
                    .append(Utils.padLeft(Integer.toString(day), 2))
                    .append("/")
                    .append(Utils.padLeft(Integer.toString(month + 1), 2))
                    .append("/")
                    .append(year);
            vueloFechaRegresoEditText.setText(fecha.toString());

            Date startDate = simpleDateFormat.parse(vueloFechaSalidaEditText.getText().toString());
            Date endDate = simpleDateFormat.parse(fecha.toString());

            if(endDate.getTime() < startDate.getTime()){
                updateStartDate();
            }

        }catch (Exception exception){
            Log.e("updateEndDate", exception.getMessage());
        }
    }

    private void buscarVuelos(){
        try{
            if(vueloOrigenSpinner.getSelectedItem() == null){
                Toast.makeText(this, "Seleccione la salida del vuelo", Toast.LENGTH_SHORT).show();
                return;
            }

            if(vueloDestinoSpinner.getSelectedItem() == null){
                Toast.makeText(this, "Seleccione el destino del vuelo", Toast.LENGTH_SHORT).show();
                return;
            }

            if(vueloNumeroAdultosEditText.getText() == null || "".equals(vueloNumeroAdultosEditText.getText().toString())){
                vueloNumeroAdultosEditText.setError("Este campo es requerido");
                return;
            }

            if(vueloNumeroChildsEditText.getText() == null || "".equals(vueloNumeroChildsEditText.getText().toString())){
                vueloNumeroChildsEditText.setError("Este campo es requerido");
                return;
            }

            if(vueloPreferredClassSpinner.getSelectedItem() == null){
                Toast.makeText(this, "Seleccione la clase preferida", Toast.LENGTH_SHORT).show();
                return;
            }

            if(vueloAirlineSpinner.getSelectedItem() == null){
                Toast.makeText(this, "Seleccione su aereolinea preferida", Toast.LENGTH_SHORT).show();
                return;
            }

            Location origen = (Location) vueloOrigenSpinner.getSelectedItem();
            Location destino = (Location) vueloDestinoSpinner.getSelectedItem();
            Airline airline = (Airline) vueloAirlineSpinner.getSelectedItem();
            FlightPreferredClass flightPreferredClass = (FlightPreferredClass) vueloPreferredClassSpinner.getSelectedItem();

            Date fechaSalida = simpleDateFormat.parse(vueloFechaSalidaEditText.getText().toString());
            Date fechaRegreso = simpleDateFormat.parse(vueloFechaRegresoEditText.getText().toString());

            Calendar cFechaSalida = Calendar.getInstance();
            Calendar cFechaRegreso = Calendar.getInstance();

            cFechaSalida.setTime(fechaSalida);
            cFechaSalida.set(Calendar.HOUR, 0);
            cFechaSalida.set(Calendar.MINUTE, 0);
            cFechaSalida.set(Calendar.SECOND, 0);

            cFechaRegreso.setTime(fechaRegreso);
            cFechaRegreso.set(Calendar.HOUR, 23);
            cFechaRegreso.set(Calendar.MINUTE, 59);
            cFechaRegreso.set(Calendar.SECOND, 59);

            Intent intent = new Intent(VueloActivity.this, VueloDetalleActivity.class);
            intent.putExtra("origenId", origen.getId());
            intent.putExtra("destinoId", destino.getId());
            intent.putExtra("lFechaSalida", cFechaSalida.getTime().getTime());
            intent.putExtra("lFechaRegreso", cFechaRegreso.getTime().getTime());
            intent.putExtra("numeroAdultos", Byte.parseByte(vueloNumeroAdultosEditText.getText().toString()));
            intent.putExtra("numeroChilds", Byte.parseByte(vueloNumeroChildsEditText.getText().toString()));
            intent.putExtra("classId", flightPreferredClass.getId());
            intent.putExtra("airlineId", airline.getId());

            startActivity(intent);

        }catch (Exception exception){
            Log.e("buscarVuelos", exception.getMessage());
        }
    }

}
