package gt.umg.viajes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Steven on 20/04/2017.
 */

public class CruceroDetalleActivity extends Activity{

    private Button seleccionarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crucero_detalle);

        seleccionarButton = (Button) findViewById(R.id.crucero_detalle_seleccionar_button);

        seleccionarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intenet = new Intent(CruceroDetalleActivity.this, CruceroInfoActivity.class);
                startActivity(intenet);

                CruceroDetalleActivity.this.finish();

            }
        });

    }
}
