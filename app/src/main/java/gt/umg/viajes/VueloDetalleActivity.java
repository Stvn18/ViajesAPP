package gt.umg.viajes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VueloDetalleActivity extends Activity {

    private Button seleccionarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo_detalle);

        seleccionarButton = (Button) findViewById(R.id.vuelo_detalle_seleccionar_button);

        seleccionarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VueloDetalleActivity.this, RegistroPasajeroActivity.class);

                startActivity(intent);

                VueloDetalleActivity.this.finish();

            }
        });

    }
}
