package gt.umg.viajes;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PagoVueloActivity extends AppCompatActivity {

    private Button confirmarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_vuelo);

        setTitle("Datos de pago");

        confirmarButton = (Button) findViewById(R.id.pago_vuelo_confirmar_button);

        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PagoVueloActivity.this, "Datos registrados!", Toast.LENGTH_SHORT).show();

                PagoVueloActivity.this.finish();

            }
        });

    }
}
