package com.iInnovations.groupchat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity implements OnCompleteListener{

    // Constants
    public static final String CHAT_PREFS = "ChatPrefs";
    public static final String DISPLAY_NAME_KEY = "username";

    // TODO: Add member variables here:
    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;

    // Firebase instance variables
    FirebaseAuth m_authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.register_email);
        mPasswordView = (EditText) findViewById(R.id.register_password);
        mConfirmPasswordView = (EditText) findViewById(R.id.register_confirm_password);
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.register_username);

        // Keyboard sign in action
        mConfirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register_form_finished || id == EditorInfo.IME_NULL)
                {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });

        // TODO: Get hold of an instance of FirebaseAuth
        m_authentication = FirebaseAuth.getInstance();
    }

    // Executed when Sign Up button is pressed.
    public void signUp(View v)
    {
        attemptRegistration();
    }

    private void attemptRegistration()
    {
        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email))
        {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        else if (!isEmailValid(email))
        {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else
        {
            // TODO: Call create FirebaseUser() here
            createFirebaseUser(email, password);
        }
    }

    private boolean isEmailValid(String email) {
        // You can add more checking logic here.
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Add own logic to check for a valid password
        String confirmPassword = mConfirmPasswordView.getText().toString();
        return confirmPassword.equals( password ) && password.length() > 4;
    }

    // TODO: Create a Firebase user
    void createFirebaseUser(final String email, final String password)
    {
        m_authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this);
    }

    // TODO: Save the display name to Shared Preferences
    private void storeDisplayNameToLocalDatabase(String s)
    {
        Log.d("FlashChat", "User name  is " + s);

        SharedPreferences prefrences = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
        prefrences.edit().putString(RegisterActivity.DISPLAY_NAME_KEY, s).apply();
    }

    // TODO: Create an alert dialog to show in case registration failed
    private void showAlertToUser(String s)
    {
        new AlertDialog.Builder( this ).setTitle("FlashChat")
                .setPositiveButton("Ok", null)
                .setMessage( s ).show();
    }

    @Override
    public void onComplete(@NonNull Task task)
    {
        if( task.isSuccessful() )
        {
            storeDisplayNameToLocalDatabase(mUsernameView.getText().toString());

            Intent loginIntent = new Intent(this, LoginActivity.class);
            finish();

            startActivity(loginIntent);
        }
        else
        {
            showAlertToUser("User registration failed!");
        }
    }

}
