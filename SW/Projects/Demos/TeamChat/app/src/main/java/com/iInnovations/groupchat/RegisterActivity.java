package com.iInnovations.groupchat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

        if ( isValidUserCredentials( ) )
        {
            // TODO: Call create FirebaseUser() here
            m_authentication.createUserWithEmailAndPassword( mEmailView.getText().toString(), mPasswordView.getText().toString() ).addOnCompleteListener(this);
        }
    }

    private boolean isValidUserCredentials()
    {
        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        final String confirmPassword = mConfirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        StringBuilder errorMsg = new StringBuilder();

        // Check for a valid password, if the user entered one.
        if (!PasswordValidator.isValidPassword(password, confirmPassword, errorMsg))
        {
            mPasswordView.setError(errorMsg.toString());
            focusView = mPasswordView;
            cancel = true;
        }

        errorMsg = new StringBuilder();

        // Check for a valid email address.
        if ( !UserNameValidator.isValidUserName(email, errorMsg) )
        {
            mEmailView.setError(errorMsg.toString());
            focusView = mEmailView;
            cancel = true;
        }

        // There was an error; don't attempt login and focus the first
        // form field with an error.
        if( cancel )
            focusView.requestFocus();

        return !cancel;
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
