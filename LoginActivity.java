package com.dinder.dinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    /** Admin's emial.*/
    private static final String ADMIN_EMAIL = "admin@admin.com";
    /** Admin's password.*/
    private static final String ADMIN_PASSWORD = "admin";
    /** Password's length.*/
    private static final int PASSWORD_LENGTH = 4;

    /** Sign In Button.*/
    private Button mySignIn;
    /** Email Text.*/
    private EditText myEmail;
    /** Password Text.*/
    private EditText myPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authenticateLogin();
    }


    /**
     * Authenticate sign in.
     */
    private void authenticateLogin() {
        mySignIn = (Button) findViewById(R.id.signIn_button);
        mySignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View theV) {
                myEmail = (EditText) findViewById(R.id.email_text);
                myPassword = (EditText) findViewById(R.id.password_text);
                if (isValidAcount(myEmail.getText().toString(), myPassword.getText().toString())) {
                    // open home page
                    Toast.makeText(LoginActivity.this, "Redirecting...", Toast.LENGTH_LONG).show();
                    Intent homepage = new Intent(LoginActivity.this, HomepageActivity.class);
                    startActivity(homepage);
                } else {
                    // display error
                    Toast.makeText(LoginActivity.this, "Wrong password or email address!", Toast.LENGTH_LONG).show();
                }
            }

            /**
             * Verify if the account is valid
             * @param theEmail email of the user
             * @param  thePassword password of the user
             * @return true if the account is valid
             */
            private boolean isValidAcount(final String theEmail, final String thePassword) {
                if(!isValidEmail(theEmail) || !isValidPassword(thePassword)) return false;
                if (!theEmail.equals(ADMIN_EMAIL) || !thePassword.equals(ADMIN_PASSWORD)) return false;

                return true;
            }

            /**
             * Verify if the email is valid
             * Pre-Condition:
             *  1. Email has to contain '@'
             * @param theEmail email of the user
             */
            private boolean isValidEmail(final String theEmail) {
                return theEmail.contains("@");
            }

            /**
             * Verify if the password is valid
             * Pre-Condition:
             *  1. Password has to be > 4
             * @param thePassword password of the user
             */
            private boolean isValidPassword(final String thePassword) {
                return thePassword.length() > PASSWORD_LENGTH;
            }

        });

    }
}


