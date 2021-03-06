package gt.umg.viajes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import gt.umg.viajes.bd.ConfigurationDb;
import gt.umg.viajes.common.Common;
import gt.umg.viajes.common.Utils;
import gt.umg.viajes.dto.CustomResponseEntityDto;
import gt.umg.viajes.dto.SessionDto;
import gt.umg.viajes.entities.UserSession;
import gt.umg.viajes.ws.ViajesWs;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageButton configurationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        configurationButton = (ImageButton) findViewById(R.id.login_configuration_button);
        configurationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfiguration();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        checkActiveSession();
    }

    /**
     * Verifica si ya hay una sesion iniciada muestra el menu principal
     */
    private void checkActiveSession(){
        try{
            ConfigurationDb configuration = new ConfigurationDb(LoginActivity.this);

            Common.setUrlWs(configuration.getConfigurationByName("WS_URL"));

            SessionDto session = configuration.getSession();

            if(session != null){
                Common.setSession(session);

                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

                this.finish();
            }

        }catch (Exception exception){
            Utils.showCustomMessage(1, exception.getMessage(), this);
        }
    }

    private void showConfiguration(){
        Intent intent = new Intent(LoginActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * Register a new user
     */
    private void signUp(){
        Intent intent  = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, CustomResponseEntityDto<UserSession>> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected CustomResponseEntityDto<UserSession> doInBackground(Void... params) {
            ViajesWs viajesWs = new ViajesWs();
            return viajesWs.login(mEmail, mPassword);
        }

        @Override
        protected void onPostExecute(final CustomResponseEntityDto<UserSession> success) {
            try{
                mAuthTask = null;
                showProgress(false);

                if(success != null && success.getResponseCode() == 201){
                    //login ok

                    //guarda la sesion en local
                    ConfigurationDb configurationDb = new ConfigurationDb(LoginActivity.this);

                    SessionDto sessionDto = new SessionDto();

                    sessionDto.setName(success.getResponseData().getUser().getName() + " " + success.getResponseData().getUser().getLastname());
                    sessionDto.setEmail(success.getResponseData().getUser().getEmail());
                    sessionDto.setToken(success.getResponseData().getToken());
                    sessionDto.setHelpActive(true);

                    configurationDb.saveSession(sessionDto.getName(), sessionDto.getEmail(), sessionDto.getToken());

                    //guarda la sesion en memoria
                    Common.setSession(sessionDto);

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

                    startActivity(intent);
                    LoginActivity.this.finish();

                } else if (success != null && success.getResponseCode() == 400){
                    //error de credenciales
                    mPasswordView.setError("Usuario o password son incorrectos");
                    mPasswordView.requestFocus();
                } else {
                    Utils.showCustomMessage(1, success.getResponseMessage(), LoginActivity.this);
                }
            }catch (Exception exception){
                Utils.showCustomMessage(1, exception.getMessage(), LoginActivity.this);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

