package gt.umg.viajes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import gt.umg.viajes.adapters.LocationAdapter;
import gt.umg.viajes.common.Utils;
import gt.umg.viajes.entities.Location;
import gt.umg.viajes.ws.ViajesWs;

public class HotelActivity extends AppCompatActivity {

    static final int DATE_DIALOG_INICIO_ID = 0;
    static final int DATE_DIALOG_FIN_ID = 1;

    private EditText fechaIngreso;
    private EditText fechaSalida;

    private Button fechaIngresoButton;
    private Button fechaSalidaButton;

    private Spinner destinosSpinner;

    private int year;
    private int month;
    private int day;

    private ViajesWs viajesWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        fechaIngreso = (EditText) findViewById(R.id.hotel_fecha_ingreso);
        fechaSalida = (EditText) findViewById(R.id.hotel_fecha_salida);
        fechaIngresoButton = (Button) findViewById(R.id.hotel_fecha_ingreso_button);
        fechaSalidaButton = (Button) findViewById(R.id.hotel_fecha_salida_button);
        destinosSpinner = (Spinner) findViewById(R.id.hotel_destino_spinner);

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

        viajesWs = new ViajesWs();

        new GetLocationListTask(this).execute();

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
            updateDateInicio();// Show the date on the TextView
        }
    };

    private void updateDateInicio() {
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
                .append(dia).append("-").append(mes).append("-").append(year));

        actualizaFechaFin();
    }

    private void actualizaFechaFin() {
        fechaSalida.setText(new StringBuilder()
                // Constant Month is 0 based so we have to add 1
                .append(day).append("-").append(month + 1).append("-")
                .append(year));
    }


    private class GetLocationListTask extends AsyncTask<Void, String, Location[]>{

        private Context context;

        public GetLocationListTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.showCustomProgressDialog("Obteniendo lista de ubicaciones", context);
        }

        @Override
        protected void onPostExecute(Location[] locations) {
            super.onPostExecute(locations);
            Utils.hideCustomProgressDialog();

            if(locations != null){

                LocationAdapter locationAdapter = new LocationAdapter(locations, context);

                destinosSpinner.setAdapter(locationAdapter);

            }
        }

        @Override
        protected Location[] doInBackground(Void... params) {
            return viajesWs.getLocationList();
        }
    }
}
