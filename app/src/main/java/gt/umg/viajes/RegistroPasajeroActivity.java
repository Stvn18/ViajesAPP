package gt.umg.viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistroPasajeroActivity extends AppCompatActivity {

    private Button continuarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasajero);

        setTitle("Datos del pasajero");

        continuarButton = (Button) findViewById(R.id.registro_pasajero_continuar_button);

        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroPasajeroActivity.this, ResumenVueloActivity.class);

                startActivity(intent);

                RegistroPasajeroActivity.this.finish();

            }
        });

    }
}
