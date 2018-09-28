package edu.unc.cs.haydenl.redaktr;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;

import java.util.Arrays;
import java.util.List;

public class SigninActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    public static final String DISPLAY_NAME = "displayName";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String UID = "uId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(this, Settings.class);
                intent.putExtra(DISPLAY_NAME, user.getDisplayName());
                intent.putExtra(EMAIL, user.getEmail());
                intent.putExtra(PHONE_NUMBER, user.getPhoneNumber());
                intent.putExtra(UID, user.getUid());
                startActivity(intent);

            } else {
                // TODO should I let them try a couple more times first?
                finish();
            }

        }
    }

    private List<AuthUI.IdpConfig> buildProviders() {
        return Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
//            new AuthUI.IdpConfig.PhoneBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());
//            new AuthUI.IdpConfig.FacebookBuilder().build(),
//            new AuthUI.IdpConfig.TwitterBuilder().build());
    }

    private void launchLogin() {
        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(buildProviders())
                .build(),
            RC_SIGN_IN);
    }
}
