package gt.umg.viajes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.viajes.R;
import gt.umg.viajes.entities.FlightPreferredClass;

/**
 * Created by wilver on 28/05/17.
 */

public class PreferredClassAdapter extends BaseAdapter {

    private FlightPreferredClass[] flightPreferredClassArray;
    private Context context;

    public PreferredClassAdapter(FlightPreferredClass[] flightPreferredClassArray, Context context){
        this.flightPreferredClassArray = flightPreferredClassArray;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);
        }

        FlightPreferredClass flightPreferredClass = flightPreferredClassArray[position];

        TextView description = (TextView) view.findViewById(R.id.template_item_spinner_text);
        description.setText(flightPreferredClass.getName());

        return view;

    }

    @Override
    public int getCount() {
        return flightPreferredClassArray.length;
    }

    @Override
    public Object getItem(int position) {
        return flightPreferredClassArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
