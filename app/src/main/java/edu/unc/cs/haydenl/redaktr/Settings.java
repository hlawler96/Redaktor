package edu.unc.cs.haydenl.redaktr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import static edu.unc.cs.haydenl.redaktr.SigninActivity.*;

public class Settings extends AppCompatActivity {

    private boolean personalInformationSwitch = false;
    private boolean socialSecuritySwitch = false;
    private boolean phoneNumberSwitch = false;
    private boolean creditCardSwitch = false;

    private String displayName;
    private String email;
    private String phoneNumber;
    private String uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            displayName = savedInstanceState.getString(DISPLAY_NAME);
            email = savedInstanceState.getString(SigninActivity.EMAIL);
            phoneNumber = savedInstanceState.getString(SigninActivity.PHONE_NUMBER);
            uId = savedInstanceState.getString(SigninActivity.UID);
        }

        setContentView(R.layout.activity_settings);
    }

    public void takePicture(View v){
        personalInformationSwitch = ((Switch) findViewById(R.id.personalInformation)).isChecked();
        socialSecuritySwitch = ((Switch) findViewById(R.id.socialSecurity)).isChecked();
        phoneNumberSwitch = ((Switch) findViewById(R.id.phoneNumbers)).isChecked();
        creditCardSwitch = ((Switch) findViewById(R.id.creditCard)).isChecked();

        Intent redaktrIntent = new Intent(this, Redaktr.class);
        redaktrIntent.putExtra(Redaktr.CREDIT_CARD_ENABLED, creditCardSwitch);
        redaktrIntent.putExtra(Redaktr.PERSONAL_INFORMATION_ENABLED, personalInformationSwitch);
        redaktrIntent.putExtra(Redaktr.PHONE_NUMBERS_ENABLED, phoneNumberSwitch);
        redaktrIntent.putExtra(Redaktr.SOCIAL_SECURITY_ENABLED, socialSecuritySwitch);
        redaktrIntent.putExtra(DISPLAY_NAME,  displayName);
        redaktrIntent.putExtra(EMAIL, email);
        redaktrIntent.putExtra(PHONE_NUMBER, phoneNumber);
        redaktrIntent.putExtra(UID, uId);
        startActivity(redaktrIntent);

    }
}