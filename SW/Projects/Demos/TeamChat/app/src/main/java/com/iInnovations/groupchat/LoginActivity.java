package com.iInnovations.groupchat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity implements OnCompleteListener
{
    // TODO: Add member variables here:
    private AutoCompleteTextView mEmailView;

    private EditText mPasswordView;

    // Cloud authentication object to perform the login
    FirebaseAuth m_auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth
        m_auth = FirebaseAuth.getInstance();
    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)
    {
        // TODO: Call attemptLogin() here
        attemptLogin();
    }

    // Executed when Register button pressed
    public void registerNewUser(View v)
    {
        Intent intent = new Intent(this, com.iInnovations.groupchat.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // TODO: Complete the attemptLogin() method
    private void attemptLogin()
    {
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        if( email.trim().isEmpty() || password.trim().isEmpty() )
            return;

        // TODO: Use FirebaseAuth to sign in with email & password
        m_auth.signInWithEmailAndPassword(email, password).addOnCompleteListener( this );
    }

    @Override
    public void onComplete(@NonNull Task task)
    {
        if( task.isSuccessful() )
        {
            Intent chatIntent = new Intent(this, MainChatActivity.class);
            finish();
            startActivity(chatIntent);
        }
        else
        {
            showAlertToUser("Login failed! Please check your credentials.");
        }
    }

    // TODO: Show error on screen with an alert dialog
    private void showAlertToUser(String s)
    {
        new AlertDialog.Builder( this ).setTitle("FlashChat")
                .setPositiveButton("Ok", null)
                .setMessage( s ).show();
    }
}