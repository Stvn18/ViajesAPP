package gt.umg.viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static gt.umg.viajes.R.id.vuelo_buscar_button;

public class VueloActivity extends AppCompatActivity {

    static final int DATE_DIALOGINIC_ID = 0;
    static final int DATE_DIALOGFIN_ID = 1;

    private Button buscarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo);

        setTitle("Seleccione su vuelo");

        buscarButton = (Button) findViewById(vuelo_buscar_button);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VueloActivity.this, VueloDetalleActivity.class);

                startActivity(intent);

                VueloActivity.this.finish();
            }
        });

    }
}
