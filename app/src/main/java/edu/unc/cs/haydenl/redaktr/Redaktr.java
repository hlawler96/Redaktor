package edu.unc.cs.haydenl.redaktr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Redaktr extends AppCompatActivity {

    public static final String PERSONAL_INFORMATION = "personalInformation";
    public static final String SOCIAL_SECURITY = "socialSecurity";
    public static final String CREDIT_CARD = "creditCard";
    public static final String PHONE_NUMBERS = "phoneNumbers";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView redactedPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redaktr);
        redactedPicture = findViewById(R.id.redactedPicture);
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            redactedPicture.setImageBitmap(imageBitmap);
        }
    }


}
