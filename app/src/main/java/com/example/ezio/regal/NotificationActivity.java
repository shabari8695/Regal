package com.example.ezio.regal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;

public class NotificationActivity extends AppCompatActivity {
    private ImageView imageHolder;
    private final int requestCode = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoCaptureIntent, requestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            Random generator=new Random();
            int n=1000;
            n=generator.nextInt(n);
            String fname="image"+n+".jpg";

            File file=new File(this.getExternalFilesDir(null),fname);

            if(file.exists())file.delete();
            try{
                FileOutputStream out=new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
                out.flush();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            File filelocation=file;//new File(file,fname);
            Uri path=Uri.fromFile(filelocation);
            Log.v("erro",path.toString());
            Intent emailInted=new Intent(Intent.ACTION_SEND);
            emailInted.setType("vnd.android.cursor.dir/email");
            String to[]={"shabari8695@gmail.com"};
            emailInted.putExtra(Intent.EXTRA_EMAIL,to);
            Date d=new Date();
            String generic_name=""+d.getDate();
            emailInted.putExtra(Intent.EXTRA_STREAM,path);
            emailInted.putExtra(Intent.EXTRA_SUBJECT,generic_name);
            startActivity(Intent.createChooser(emailInted,"sending email..."));
        }
    }

}