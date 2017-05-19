package gt.umg.viajes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import gt.umg.viajes.R;
import gt.umg.viajes.entities.HotelDetail;

/**
 * Created by wilver on 18/05/17.
 */

public class HotelServiceAdapter extends ArrayAdapter<HotelDetail> {

    public HotelServiceAdapter(Context context, HotelDetail[] services) {
        super(context, 0, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_hotel_service, parent, false);
        }

        TextView nombreHotelTextView = (TextView) convertView.findViewById(R.id.hotel_service_name);
        TextView numeroHabitacionesTextView = (TextView) convertView.findViewById(R.id.hotel_service_numero_habitaciones);
        TextView numeroAdultosTextView = (TextView) convertView.findViewById(R.id.hotel_service_numero_adultos);
        TextView numeroChildsTextView = (TextView) convertView.findViewById(R.id.hotel_service_numero_childs);
        TextView descripcionTextView = (TextView) convertView.findViewById(R.id.hotel_service_descripcion);
        TextView precioTextView = (TextView) convertView.findViewById(R.id.hotel_service_price);

        HotelDetail hotelDetail = getItem(position);

        nombreHotelTextView.setText(hotelDetail.getHotel().getName());
        numeroHabitacionesTextView.setText("Habitaciones: " + hotelDetail.getBedrooms().toString());
        numeroAdultosTextView.setText("Adultos: " + hotelDetail.getAdults());
        numeroChildsTextView.setText("Niños: " + hotelDetail.getChildrens());
        descripcionTextView.setText(hotelDetail.getDescription());
        precioTextView.setText("$" + hotelDetail.getPrice().toString());

        return convertView;
    }

    @Override
    public HotelDetail getItem(int position) {
        return super.getItem(position);
    }

}
