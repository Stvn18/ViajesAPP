package gt.umg.viajes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Steven on 20/04/2017.
 */

public class CruceroInfoActivity extends Activity{

    private Button continuarButton;
    private Button regresarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crucero_info);

        continuarButton = (Button) findViewById(R.id.crucero_info_continuar_button);

        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CruceroInfoActivity.this, CruceroResumenFacturacionActivity.class);

                startActivity(intent);

                CruceroInfoActivity.this.finish();

            }
        });

        regresarButton = (Button) findViewById(R.id.crucero_info_regresar_button);

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CruceroInfoActivity.this, CruceroDetalleActivity.class);

                startActivity(intent);

                CruceroInfoActivity.this.finish();

            }
        });

    }

}
