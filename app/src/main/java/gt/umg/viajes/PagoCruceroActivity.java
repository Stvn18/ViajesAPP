package gt.umg.viajes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Steven on 21/04/2017.
 */

public class PagoCruceroActivity extends AppCompatActivity {

    private Button confirmarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_crucero);

        setTitle("Datos de Pago");

        confirmarButton = (Button) findViewById(R.id.pago_crucero_confirmar_button);

        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PagoCruceroActivity.this, "Datos registrados!", Toast.LENGTH_SHORT).show();

                PagoCruceroActivity.this.finish();

            }
        });

    }

}
