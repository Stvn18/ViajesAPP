package gt.umg.viajes;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import gt.umg.viajes.common.Utils;
import gt.umg.viajes.dto.CustomResponseEntityDto;
import gt.umg.viajes.entities.User;
import gt.umg.viajes.ws.ViajesWs;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName;
    private EditText registerLastname;
    private EditText registerEmail;
    private EditText registerPassword;
    private Button registerButton;

    private ProgressBar progressBar;
    private ScrollView registerForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerName = (EditText) findViewById(R.id.register_name);
        registerLastname = (EditText) findViewById(R.id.register_lastname);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerButton = (Button) findViewById(R.id.register_button);
        progressBar = (ProgressBar) findViewById(R.id.register_progress);
        registerForm = (ScrollView) findViewById(R.id.register_form);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void signUp(){

        if(registerName.getText().toString() == null || "".equals(registerName.getText().toString())){
            registerName.setError("Ingrese su nombre");
            return;
        }

        if(registerLastname.getText().toString() == null || "".equals(registerLastname.getText().toString())){
            registerLastname.setError("Ingrese su apellido");
            return;
        }

        if(registerEmail.getText().toString() == null || "".equals(registerEmail.getText().toString())){
            registerEmail.setError("Ingrese su email");
            return;
        }

        if(registerPassword.getText().toString() == null || "".equals(registerPassword.getText().toString())){
            registerPassword.setError("Ingrese su password");
        }

        User user = new User();

        user.setName(registerName.getText().toString());
        user.setLastname(registerLastname.getText().toString());
        user.setEmail(registerEmail.getText().toString());
        user.setPassword(registerPassword.getText().toString());

        new UserSignUpTask(this, user).execute();

    }

    private void showProgress(boolean show){
        registerForm.setVisibility(show ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    
    private class UserSignUpTask extends AsyncTask<Void, String, CustomResponseEntityDto<User>>{

        private Context context;
        private User user;

        public UserSignUpTask(Context context, User user){
            this.context = context;
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected CustomResponseEntityDto<User> doInBackground(Void... params) {
            return new ViajesWs().signUp(user);
        }

        @Override
        protected void onPostExecute(CustomResponseEntityDto<User> response) {
            super.onPostExecute(response);
            showProgress(false);

            if(response.getResponseCode() == 201){

                Toast.makeText(RegisterActivity.this, "Te has registrado exitosamente!", Toast.LENGTH_LONG).show();
                RegisterActivity.this.finish();

            } else {
                Utils.showCustomMessage(1, response.getResponseMessage(), context);
            }

        }

    }


}
