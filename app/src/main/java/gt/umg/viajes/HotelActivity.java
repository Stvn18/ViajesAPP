package gt.umg.viajes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import gt.umg.viajes.adapters.LocationAdapter;
import gt.umg.viajes.common.Utils;
import gt.umg.viajes.entities.Hotel;
import gt.umg.viajes.entities.HotelDetail;
import gt.umg.viajes.entities.Location;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

public class HotelActivity extends AppCompatActivity {

    static final int DATE_DIALOG_INICIO_ID = 0;
    static final int DATE_DIALOG_FIN_ID = 1;

    private EditText fechaIngreso;
    private EditText fechaSalida;

    private Button fechaIngresoButton;
    private Button fechaSalidaButton;
    private Spinner destinosSpinner;
    private EditText numeroHabitacionesEditText;
    private EditText numeroAdultosEditText;
    private EditText numeroChildsEditText;
    private Button searchButton;

    private int year;
    private int month;
    private int day;

    private ViajesWs viajesWs;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        fechaIngreso = (EditText) findViewById(R.id.hotel_fecha_ingreso);
        fechaSalida = (EditText) findViewById(R.id.hotel_fecha_salida);
        fechaIngresoButton = (Button) findViewById(R.id.hotel_fecha_ingreso_button);
        fechaSalidaButton = (Button) findViewById(R.id.hotel_fecha_salida_button);
        destinosSpinner = (Spinner) findViewById(R.id.hotel_destino_spinner);

        numeroHabitacionesEditText = (EditText) findViewById(R.id.hotel_numero_habitaciones);
        numeroAdultosEditText = (EditText) findViewById(R.id.hotel_numero_adultos);
        numeroChildsEditText = (EditText) findViewById(R.id.hotel_numero_childs);
        searchButton = (Button) findViewById(R.id.hotel_buscar_button);

        fechaIngresoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_INICIO_ID);
            }
        });

        fechaSalidaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_FIN_ID);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        viajesWs = new ViajesWs();

        //new GetLocationListTask(this).execute();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        updateDateInicio();

        viajesWs.getLocationArray().execute(new ResourceResponse<Location[]>() {
            @Override
            public void success(int statusCode, Location[] responseData) {
                if(statusCode == 200){
                    LocationAdapter locationAdapter = new LocationAdapter(responseData, HotelActivity.this);
                    destinosSpinner.setAdapter(locationAdapter);
                }
            }

            @Override
            public void error(int errorCode, String error) {

            }
        });

    }

    private void search(){
        try{
            if("".equals(numeroHabitacionesEditText.getText().toString()) || "".equals(numeroAdultosEditText.getText().toString()) || "".equals(numeroChildsEditText.getText())){
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Location location = (Location) destinosSpinner.getSelectedItem();

            Date fechaInicio = sdf.parse(fechaIngreso.getText().toString() + " 00:00:00");
            Date fechaFin = sdf.parse(fechaSalida.getText().toString() + " 23:59:59");

            Intent intent = new Intent(HotelActivity.this, HotelSearchResultActivity.class);
            intent.putExtra("locationId", location.getId().toString());
            intent.putExtra("childrens", numeroChildsEditText.getText().toString());
            intent.putExtra("adults", numeroAdultosEditText.getText().toString());
            intent.putExtra("bedrooms", numeroHabitacionesEditText.getText().toString());
            intent.putExtra("lDateIn", fechaInicio.getTime());
            intent.putExtra("lDateOut", fechaFin.getTime());

            startActivity(intent);

        }catch (Exception exception){

        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_INICIO_ID:
                return new DatePickerDialog(this, mDateSetListenerInicio, year, month, day);

            case DATE_DIALOG_FIN_ID:
                return new DatePickerDialog(this, mDateSetListenerFin, year, month, day);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener mDateSetListenerInicio = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int yearOf, int monthOfYear,
                              int dayOfMonth) {
            year = yearOf;
            month = monthOfYear;
            day = dayOfMonth;
            updateDateInicio();// Show the date on the TextView
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListenerFin = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int yearOf, int monthOfYear,
                              int dayOfMonth) {
            year = yearOf;
            month = monthOfYear;
            day = dayOfMonth;
            actualizaFechaFin();// Show the date on the TextView
        }
    };

    private void updateDateInicio() {
        try{
            fechaIngresoButton.setEnabled(true);
            String dia = "" + day;
            String mes = "" + (month + 1);

            if (day < 10) {
                dia = "0" + dia;
            }

            if ((month + 1) < 10) {
                mes = "0" + mes;
            }
            fechaIngreso.setText(new StringBuilder()
                    // Constant Month is 0 based so we have to add 1
                    .append(dia).append("/").append(mes).append("/").append(year));

            if(fechaSalida.getText() != null && !"".equals(fechaSalida.getText().toString())){
                Date fechaInicio = simpleDateFormat.parse(fechaIngreso.getText().toString());
                Date fechaFin = simpleDateFormat.parse(fechaSalida.getText().toString());

                if(fechaInicio.getTime() > fechaFin.getTime()){
                    fechaSalida.setText(simpleDateFormat.format(fechaInicio));
                }
            } else {
                actualizaFechaFin();
            }
        }catch (ParseException e){

        }
    }

    private void actualizaFechaFin() {
        try {
            fechaSalida.setText(new StringBuilder()
                    // Constant Month is 0 based so we have to add 1
                    .append(day).append("/").append(month + 1).append("/")
                    .append(year));

            Date fechaInicio = simpleDateFormat.parse(fechaIngreso.getText().toString());
            Date fechaFin = simpleDateFormat.parse(fechaSalida.getText().toString());

            if(fechaFin.getTime() < fechaInicio.getTime()){
                fechaIngreso.setText(simpleDateFormat.format(fechaFin));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
