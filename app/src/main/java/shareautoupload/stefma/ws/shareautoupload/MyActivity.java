package shareautoupload.stefma.ws.shareautoupload;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            handleImage(intent);
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            handleSendMultipleImages(intent);
        }


        finish();
    }

    void handleImage(Intent intent) {
        Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            shareIntent.setComponent(new ComponentName("com.google.android.apps.plus",
                    "com.google.android.apps.photos.phone.SendContentActivityAlias"));
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.setType("image/jpeg");
            startActivity(shareIntent);
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            Intent shareIntent = new Intent();
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            shareIntent.setComponent(new ComponentName("com.google.android.apps.plus",
                    "com.google.android.apps.photos.phone.SendContentActivityAlias"));
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("*/*");
            startActivity(shareIntent);
        }
    }
}
