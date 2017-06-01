package gt.umg.viajes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.viajes.R;
import gt.umg.viajes.entities.Location;
import gt.umg.viajes.entities.PaymentType;

/**
 * Created by wilver on 31/05/17.
 */

public class TipoPagoAdapter extends BaseAdapter {

    private PaymentType[] tipoPagoArray;
    private Context context;

    public TipoPagoAdapter(PaymentType[] tipoPagoArray, Context context){
        this.tipoPagoArray = tipoPagoArray;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);
        }

        PaymentType paymentType = tipoPagoArray[position];

        TextView description = (TextView) view.findViewById(R.id.template_item_spinner_text);
        description.setText(paymentType.getDescription());

        return view;

    }

    @Override
    public int getCount() {
        return tipoPagoArray.length;
    }

    @Override
    public Object getItem(int position) {
        return tipoPagoArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
