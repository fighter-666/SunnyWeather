package com.example.sunnyweather.pran;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import com.example.sunnyweather.R;
import com.example.sunnyweather.activity.SharePreference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Camera2Activity extends AppCompatActivity {   // 申请相机权限的requestCode
   private static final int PERMISSION_CAMERA_REQUEST_CODE = 0x00000012;
   private static final int CAMERA_REQUEST_CODE = 6666;
   //用于保存拍照图片的uri
   private Uri mCameraUri;

   // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
   private String mCameraImagePath;
   private ImageView ivPhoto;

   // 是否是Android 10以上手机
   private boolean isAndroidQ = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_camera2);
      ivPhoto = (ImageView) findViewById(R.id.imageView);
      checkPermissionAndCamera();
      Button cameraButton = findViewById(R.id.camera);

// 设置点击事件
      cameraButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            camera();
         }
      });
   }


   /**
    * 调起相机拍照
    */
   private void camera() {
      Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      // 判断是否有相机
      if (captureIntent.resolveActivity(getPackageManager()) != null) {
         File photoFile = null;
         Uri photoUri = null;

         if (isAndroidQ) {
            // 适配android 10
            photoUri = createImageUri();
         } else {
            try {
               photoFile = createImageFile();
            } catch (IOException e) {
               e.printStackTrace();
            }

            if (photoFile != null) {
               mCameraImagePath = photoFile.getAbsolutePath();
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                  photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
               } else {
                  photoUri = Uri.fromFile(photoFile);
               }
            }
         }

         mCameraUri = photoUri;
         if (photoUri != null) {
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
         }
      }
   }

   /**
    * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
    */
   private Uri createImageUri() {
      String status = Environment.getExternalStorageState();
      // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
      if (status.equals(Environment.MEDIA_MOUNTED)) {
         return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
      } else {
         return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
      }
   }

   /**
    * 创建保存图片的文件
    */
   private File createImageFile() throws IOException {
      String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
      File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
      if (!storageDir.exists()) {
         storageDir.mkdir();
      }
      File tempFile = new File(storageDir, imageName);
      if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
         return null;
      }
      return tempFile;
   }



   /**
    * 检查权限并拍照。
    * 调用相机前先检查权限。
    */
   private void checkPermissionAndCamera() {
      int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
              Manifest.permission.CAMERA);
      if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
         //有调起相机拍照。
         //camera();
      } else {
         //没有权限，申请权限。
         ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                 PERMISSION_CAMERA_REQUEST_CODE);
      }
   }

   /**
    * 处理权限申请的回调。
    */
   @Override
   public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
         if (grantResults.length > 0
                 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //允许权限，有调起相机拍照。
            camera();
         } else {
            //拒绝权限，弹出提示框。
            Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_LONG).show();
         }
      }
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == CAMERA_REQUEST_CODE) {
         if (resultCode == RESULT_OK) {
            if (isAndroidQ) {
               // Android 10 使用图片uri加载
               ivPhoto.setImageURI(mCameraUri);
            } else {
               // 使用图片路径加载
               ivPhoto.setImageBitmap(BitmapFactory.decodeFile(mCameraImagePath));
            }
         } else {
            Toast.makeText(this,"取消",Toast.LENGTH_LONG).show();
         }
      }
   }


}
