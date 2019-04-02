package com.example.xyp.e_mail;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WriteActivity extends AppCompatActivity {

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_mail);
        Button button = (Button) findViewById(R.id.takePhoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File myPhoto = new File(getExternalCacheDir(),"myPhoto.jpg");
                if(myPhoto.exists())
                    myPhoto.delete();
                try {
                    myPhoto.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri = FileProvider.getUriForFile(WriteActivity.this,"com.example.xyp.e_mail.WriteActivity",myPhoto);
                }
                else {
                    imageUri = Uri.fromFile(myPhoto);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,100);

            }
        });



        //选则图片
        Button button2 = (Button) findViewById(R.id.selectPhoto);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(WriteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(WriteActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
                else {
                    openAlbum();
//                Log.d("SelectPhoto","READ");
                }
            }
        });

    }


    void openAlbum(){
        Intent intent= new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult){
        switch (requestCode){
            case 4:
                if(grantResult.length>0&&grantResult[0]==PackageManager.PERMISSION_GRANTED)
                    openAlbum();
                else Toast.makeText(WriteActivity.this,"you denied the permission",Toast.LENGTH_SHORT).show();
                Log.d("SelectPhoto","READ");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2:
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }
                    else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            case  100:
                if(resultCode==RESULT_OK){
                    try {
                        Log.d("debug",imageUri.toString());
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        ImageView imageView = (ImageView) findViewById(R.id.showPhoto);
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @TargetApi(19)
    void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(WriteActivity.this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection =  MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);

            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }

        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);

        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();

        }
        displayImage(imagePath);
        Log.d("SelectPhoto","OOOONN");
    }

    void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
        Log.d("SelectPhoto","OOOOOO");
    }

    String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null,null);
//        Log.d("SelectPhoto","query");
        if(cursor!=null){
            if(cursor.moveToFirst())
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
        }
        return path;
    }

    void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap= BitmapFactory.decodeFile(imagePath);
            ImageView imageView = (ImageView)findViewById(R.id.showPhoto);
            imageView.setImageBitmap(bitmap);
        }
        Log.v("hh","hehe");
    }
}
