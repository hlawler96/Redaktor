package edu.unc.cs.haydenl.redaktr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private boolean personalInformation = false;
    private boolean socialSecurity = false;
    private boolean phoneNumber = false;
    private boolean creditCard = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void takePicture(View v){
        personalInformation = ((Switch) findViewById(R.id.personalInformation)).isChecked();
        socialSecurity = ((Switch) findViewById(R.id.socialSecurity)).isChecked();
        phoneNumber = ((Switch) findViewById(R.id.phoneNumbers)).isChecked();
        creditCard = ((Switch) findViewById(R.id.creditCard)).isChecked();

        Intent redaktrIntent = new Intent(this, Redaktr.class);
        redaktrIntent.putExtra(Redaktr.CREDIT_CARD, creditCard);
        redaktrIntent.putExtra(Redaktr.PERSONAL_INFORMATION, personalInformation);
        redaktrIntent.putExtra(Redaktr.PHONE_NUMBERS, phoneNumber);
        redaktrIntent.putExtra(Redaktr.SOCIAL_SECURITY, socialSecurity);
        startActivity(redaktrIntent);

    }
}
