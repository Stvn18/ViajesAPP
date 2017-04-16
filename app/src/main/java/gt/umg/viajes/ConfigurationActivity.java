package gt.umg.viajes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gt.umg.viajes.bd.ConfigurationDb;
import gt.umg.viajes.common.Utils;

public class ConfigurationActivity extends Activity {

    private EditText configurationUrlWs;
    private Button configurationSaveButton;
    private ConfigurationDb configurationDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        configurationUrlWs = (EditText) findViewById(R.id.configuration_url_ws);
        configurationSaveButton = (Button) findViewById(R.id.configuration_save_button);

        configurationSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfiguration();
            }
        });

        configurationDb = new ConfigurationDb(this);

        try{
            configurationUrlWs.setText(configurationDb.getConfigurationByName("WS_URL"));
        }catch (Exception exception){
            Utils.showCustomMessage(1, exception.getMessage(), this);
        }

    }

    private void saveConfiguration() {
        try{

            if(configurationUrlWs.getText().toString() == null || "".equals(configurationUrlWs.getText().toString())){
                return;
            }

            configurationDb.updateConfiguration("WS_URL", configurationUrlWs.getText().toString().trim());

            Intent intent = new Intent(ConfigurationActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();

        }catch (Exception exception){
            Utils.showCustomMessage(1, exception.getMessage(), this);
        }
    }
}