package com.example.voiceencryption;

import static com.example.voiceencryption.Utility.getOnlyStrings;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    Button button;
    Button btnText;
    Button btnStop;
    Button btnText2;
    MediaPlayer player = new MediaPlayer();
    File file;
    int REQUEST_WRITE_PERMISSION = 1;
    TextToSpeech textToSpeech;
    String encry = null;
    String decry = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        button = findViewById(R.id.btnPlayAudio);
        btnText = findViewById(R.id.btnPlayAudio2);
        btnStop = findViewById(R.id.btnStopAudio);
        btnText2 = findViewById(R.id.btnPlayAudio3);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.stop();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSound();
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(encry.substring(2,30),TextToSpeech.QUEUE_FLUSH,null);

            }
        });

        btnText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textToSpeech.speak(decry.substring(2,30),TextToSpeech.QUEUE_FLUSH,null);
//                    finishSound();
            }
        });

    }

    private void startSound() {
        AssetFileDescriptor afd = null;
        try{
            afd = getResources().getAssets().openFd("mirrage2.mp3");
            file = new File(Environment.getExternalStorageDirectory()+ "/mirrage2.mp3");
            byte [] bytes = FileUtils.readFileToByteArray(file);
            String encoded = Base64.encodeToString(bytes,0);
            CipherEncrypt(encoded,5);
            byte[] decoded = Base64.decode(encoded,0);

            try {
                File file2 = new File(Environment.getExternalStorageDirectory() + "/hello-1.wav");
                FileOutputStream os = new FileOutputStream(file2,true);
                os.write(decoded);
                os.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
//afd asset File Descriptor
            assert  afd != null;
            player.setDataSource(file.toString());
            player.prepare();
            player.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_WRITE_PERMISSION);
        }
    }

    private void finishSound() {
        AssetFileDescriptor afd = null;
        try{
            afd = getResources().getAssets().openFd("/jirrage.mp3");
            file = new File(Environment.getExternalStorageDirectory()+ "/jirrage.mp3");
            byte [] bytes = FileUtils.readFileToByteArray(file);
            String encoded = Base64.encodeToString(bytes,0);
            CipherDecrypt(encoded,5);
            byte[] decoded = Base64.decode(encoded,0);

            /*try {
                File file3 = new File(Environment.getExternalStorageDirectory() + "/hello-2.wav");
                FileOutputStream os = new FileOutputStream(file3,true);
                os.write(decoded);
                os.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }*/

        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
//afd asset File Descriptor
            assert  afd != null;
            player.setDataSource(file.toString());
            player.prepare();
            player.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }





//Caesar Cipher
    public void CipherEncrypt(String text, int shift){
        StringBuffer result = new StringBuffer();
        for(int i=0; i<text.length(); i++){
            if (Character.isUpperCase(text.charAt(i))){
                char ch = (char)(((int)text.charAt(i) + shift -65)% 26+65);
                result.append(ch);
            }
            else{
                char ch = (char) (((int)text.charAt(i) + shift -97)% 26+97);
                result.append(ch);
            }
        }
        encry = getOnlyStrings(result.toString());
        try {
            byte[] decoded = Base64.decode(result.toString(),0);
            File file2 = new File(Environment.getExternalStorageDirectory() + "/jirage.mp3");
            FileOutputStream os = new FileOutputStream(file2,true);
            os.write(decoded);
            os.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void CipherDecrypt(String encry, int shift){
        StringBuffer result = new StringBuffer();
        for(int i=0; i<encry.length(); i++){
            if (Character.isUpperCase(encry.charAt(i))){
                char ch = (char)(((int)encry.charAt(i) + shift -65)% 26+65);
                result.append(ch);
            }
            else{
                char ch = (char) (((int)encry.charAt(i) + shift -97)% 26+97);
                result.append(ch);
            }
        }
        decry = getOnlyStrings(result.toString());
        try {
            byte[] decoded = Base64.decode(result.toString(),0);
            File file3 = new File(Environment.getExternalStorageDirectory() + "/jirrage.mp3");
            FileOutputStream os = new FileOutputStream(file3,true);
            os.write(decoded);
            os.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}