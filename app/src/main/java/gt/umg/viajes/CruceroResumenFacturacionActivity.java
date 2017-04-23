package gt.umg.viajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Steven on 21/04/2017.
 */

public class CruceroResumenFacturacionActivity extends AppCompatActivity {

    private Button reservarButton;
    private Button regresarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crucero_resumen);
        setTitle("Datos de Facturación");

        reservarButton = (Button) findViewById(R.id.resumen_crucero_reservar_button);

        reservarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CruceroResumenFacturacionActivity.this, PagoCruceroActivity.class);
                startActivity(intent);

                CruceroResumenFacturacionActivity.this.finish();
            }
        });

        regresarButton = (Button) findViewById(R.id.resumen_crucero_regresar_button);

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CruceroResumenFacturacionActivity.this, CruceroInfoActivity.class);
                startActivity(intent);

                CruceroResumenFacturacionActivity.this.finish();
            }
        });

    }
}
