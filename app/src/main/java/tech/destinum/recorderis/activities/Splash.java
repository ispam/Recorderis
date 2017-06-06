package tech.destinum.recorderis.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import tech.destinum.recorderis.R;

public class Splash extends AppCompatActivity {

    private VideoView video_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        video_view = (VideoView) findViewById(R.id.video_view);

        try {


            Uri path = Uri.parse("android.resource://"+ getPackageName()+"/"+ R.raw.splash_screen);
            video_view.setVideoURI(path);

            video_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    Intent intent = new Intent(Splash.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


                }
            });

            video_view.start();

        } catch (Exception e){

        }
    }


}
