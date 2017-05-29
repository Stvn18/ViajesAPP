package gt.umg.viajes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import gt.umg.viajes.R;
import gt.umg.viajes.entities.Flight;
import gt.umg.viajes.entities.HotelDetail;

/**
 * Created by wilver on 28/05/17.
 */

public class VuelosAdapter extends ArrayAdapter<Flight> {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public VuelosAdapter(Context context, Flight[] vuelos) {
        super(context, 0, vuelos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_vuelo_detalle, parent, false);
        }

        Flight flight = getItem(position);

        TextView aereoLinea = (TextView) convertView.findViewById(R.id.layout_vuelo_detalle_aereolinea);
        TextView precio = (TextView) convertView.findViewById(R.id.layout_vuelo_detalle_precio);
        TextView fechaSalidaLlegada = (TextView) convertView.findViewById(R.id.layout_vuelo_detalle_fecha_salida_llegada);
        TextView origenDestino = (TextView) convertView.findViewById(R.id.layout_vuelo_detalle_origen_destino);
        TextView tiempoVuelo = (TextView) convertView.findViewById(R.id.layout_vuelo_detalle_tiempo_vuelo);
        TextView descripcion = (TextView) convertView.findViewById(R.id.layout_vuelo_detalle_descripcion);

        aereoLinea.setText(flight.getAirline().getName());
        precio.setText("$" + flight.getPrice());
        fechaSalidaLlegada.setText("Salida " + simpleDateFormat.format(flight.getDepartingDate()) + " - Retorno " + simpleDateFormat.format(flight.getReturningDate()));
        origenDestino.setText(flight.getFlyingFrom().getLocationName() + " - " + flight.getFlyingTo().getLocationName());
        tiempoVuelo.setText("0 Horas");
        descripcion.setText(flight.getDescription());

        return convertView;
    }

    @Override
    public Flight getItem(int position) {
        return super.getItem(position);
    }

}
