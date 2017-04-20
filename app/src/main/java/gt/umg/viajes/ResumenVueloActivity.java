package gt.umg.viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResumenVueloActivity extends AppCompatActivity {

    private Button continuarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_vuelo);
        setTitle("Resumen de facturación");

        continuarButton = (Button) findViewById(R.id.resumen_vuelo_continuar_button);

        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumenVueloActivity.this, PagoVueloActivity.class);
                startActivity(intent);

                ResumenVueloActivity.this.finish();
            }
        });

    }

}
