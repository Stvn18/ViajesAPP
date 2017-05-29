package gt.umg.viajes.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import gt.umg.viajes.R;

/**
 * Created by wilver on 13/04/17.
 */

public class Utils {

    private static ProgressDialog progressDialog;

    public static void showCustomMessage(final int type, String message, final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog);
        dialog.setCancelable(false);

        TextView textViewCloseMessage = (TextView) dialog.findViewById(R.id.text_view_custom_dialog_close);
        TextView textViewMessage = (TextView) dialog.findViewById(R.id.text_view_custom_dialog_message);

        textViewMessage.setText(message);
        Vibrator vibrator;

        switch (type) {
            case 1:
                textViewMessage.setTextColor(Color.RED);
                vibrator = (Vibrator) context.getSystemService(context.getApplicationContext().VIBRATOR_SERVICE);
                vibrator.vibrate(1000);


                break;
            default:
                break;
        }

        textViewCloseMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (type == 3) {
                    ((Activity) context).finish();
                }

            }
        });

        dialog.show();
    }

    public static void showCustomProgressDialog(String message, Context context) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public static void hideCustomProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static void updateMessageProgressDialog(String message) {
        progressDialog.setMessage(message);
    }

    public static String padLeft(String s, int n) {
        StringBuilder sb = new StringBuilder();

        for (int toPrepend = n - s.length(); toPrepend > 0; toPrepend--) {
            sb.append('0');
        }

        sb.append(s);
        String result = sb.toString();

        return result;
    }

}
