package gt.umg.viajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Steven on 20/04/2017.
 */

public class CruceroActivity extends AppCompatActivity {

    static final int DATE_DIALOGINIC_ID = 0;
    static final int DATE_DIALOGFIN_ID = 1;

    private Button buscarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crucero);

        setTitle("Seleccione su Crucero");

        buscarButton = (Button) findViewById(R.id.crucero_buscar_button);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CruceroActivity.this, CruceroDetalleActivity.class);

                startActivity(intent);

                CruceroActivity.this.finish();
            }
        });

    }
}
