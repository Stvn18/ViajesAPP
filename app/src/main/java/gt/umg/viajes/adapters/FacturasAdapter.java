package gt.umg.viajes.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import gt.umg.viajes.R;
import gt.umg.viajes.common.Utils;
import gt.umg.viajes.entities.Invoice;

/**
 * Created by wilver on 2/06/17.
 */

public class FacturasAdapter extends ArrayAdapter<Invoice> {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    NumberFormat formatter = new DecimalFormat("#0.00");

    public FacturasAdapter(Context context, Invoice[] facturas) {
        super(context, 0, facturas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_listado_facturas, parent, false);
        }

        Invoice invoice = getItem(position);

        TextView numeroFactura = (TextView) convertView.findViewById(R.id.layout_listado_facturas_numero);
        TextView fechaFactura = (TextView) convertView.findViewById(R.id.layout_listado_facturas_fecha);
        TextView totalFactura = (TextView) convertView.findViewById(R.id.layout_listado_facturas_total);

        numeroFactura.setText(Utils.padLeft(invoice.getId().toString(), 8));
        fechaFactura.setText(simpleDateFormat.format(invoice.getCreatedAt()));
        totalFactura.setText(formatter.format(invoice.getTotal()));

        return convertView;
    }

    @Override
    public Invoice getItem(int position) {
        return super.getItem(position);
    }

}
