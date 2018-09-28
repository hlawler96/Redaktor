package edu.unc.cs.haydenl.redaktr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    public void takePicture(){

    }
}
