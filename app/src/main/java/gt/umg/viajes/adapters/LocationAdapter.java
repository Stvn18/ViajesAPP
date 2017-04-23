package gt.umg.viajes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.viajes.R;
import gt.umg.viajes.entities.Location;

/**
 * Created by wilver on 23/04/17.
 */
public class LocationAdapter extends BaseAdapter {

    private Location[] locationArray;
    private Context context;

    public LocationAdapter(Location[] locationArray, Context context){
        this.locationArray = locationArray;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);
        }

        Location location = locationArray[position];

        TextView description = (TextView) view.findViewById(R.id.template_item_spinner_text);
        description.setText(location.getLocationName());

        return view;

    }

    @Override
    public int getCount() {
        return locationArray.length;
    }

    @Override
    public Object getItem(int position) {
        return locationArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
