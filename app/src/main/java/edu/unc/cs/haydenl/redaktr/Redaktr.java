package edu.unc.cs.haydenl.redaktr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class Redaktr extends AppCompatActivity {

    public static final String PERSONAL_INFORMATION = "personalInformation";
    public static final String SOCIAL_SECURITY = "socialSecurity";
    public static final String CREDIT_CARD = "creditCard";
    public static final String PHONE_NUMBERS = "phoneNumbers";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView redactedPicture;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redaktr);
        redactedPicture = findViewById(R.id.redactedPicture);
        dispatchTakePictureIntent();
        context = this;
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
            redactPicture(imageBitmap);

        }
    }

    private void redactPicture(Bitmap b){
        FirebaseApp.initializeApp(this);
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(b);
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText result) {
                        Log.d("RESULT", result.getText());
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Sorry, we couldn't recognize that photo. Please try again", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dispatchTakePictureIntent();
                                    }
                                }, 2000);

                            }
                        });


    }




}
