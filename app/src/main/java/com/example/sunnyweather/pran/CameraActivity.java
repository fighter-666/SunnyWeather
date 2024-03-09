package com.example.sunnyweather.pran;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.sunnyweather.R;
import com.example.sunnyweather.activity.SharePreference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

   private static final int PHOTO_FROM_GALLERY = 1;
   private static final int PHOTO_FROM_CAMERA = 2;
   private ImageView imageView;
   private File appDir;
   private Uri uriForCamera;
   private Date date;
   private String str = "";
   private SharePreference sharePreference;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_camera);
      //Android不推荐使用全局变量，我在这里使用了sharePreference
      sharePreference = SharePreference.getInstance(this);
      imageView = (ImageView) findViewById(R.id.imageView);
   }

   //从相册取图片
   public void gallery(View view) {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      startActivityForResult(intent, PHOTO_FROM_GALLERY);
   }

   //拍照取图片
   public void camera(View view) {
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

     /* // 在camera方法中
      File photoFile = createImageStoragePath();
      uriForCamera = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", photoFile);
      sharePreference.setCache("uri", uriForCamera.toString());*/

      uriForCamera = Uri.fromFile(createImageStoragePath());
      sharePreference.setCache("uri", String.valueOf(uriForCamera));

      /**
       *  指定了uri路径，startActivityForResult不返回intent，
       *  所以在onActivityResult()中不能通过data.getData()获取到uri;
       */
      intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForCamera);
      startActivityForResult(intent, PHOTO_FROM_CAMERA);
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      //第一层switch
      switch (requestCode) {
         case PHOTO_FROM_GALLERY:
            //第二层switch
            switch (resultCode) {
               case RESULT_OK:
                  if (data != null) {
                     Uri uri = data.getData();
                     imageView.setImageURI(uri);
                  }
                  break;
               case RESULT_CANCELED:
                  break;
            }
            break;
         case PHOTO_FROM_CAMERA:
            if (resultCode == RESULT_OK) {
               Uri uri = Uri.parse(sharePreference.getString("uri"));
               updateDCIM(uri);
               try {
                  //把URI转换为Bitmap，并将bitmap压缩，防止OOM(out of memory)
                  Bitmap bitmap = ImageTools.getBitmapFromUri(uri, this);
                  imageView.setImageBitmap(bitmap);
               } catch (IOException e) {
                  e.printStackTrace();
               }

               removeCache("uri");
            } else {
               Log.e("result", "is not ok" + resultCode);
            }
            break;
         default:
            break;
      }
   }

   /**
    * 设置相片存放路径，先将照片存放到SD卡中，再操作
    *
    * @return
    */
   private File createImageStoragePath() {
      if (hasSdcard()) {
         appDir = new File("/sdcard/testImage/");
         if (!appDir.exists()) {
            appDir.mkdirs();
         }
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
         date = new Date();
         str = simpleDateFormat.format(date);
         String fileName = str + ".jpg";
         File file = new File(appDir, fileName);
         return file;
      } else {
         Log.e("sd", "is not load");
         return null;
      }
   }

   /**
    * 将照片插入系统相册，提醒相册更新
    *
    * @param uri
    */
   private void updateDCIM(Uri uri) {
      Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
      intent.setData(uri);
      this.sendBroadcast(intent);

      Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
      MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "", "");
   }

   /**
    * 判断SD卡是否可用
    *
    * @return
    */
   private boolean hasSdcard() {
      if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * 移除缓存
    *
    * @param cache
    */
   private void removeCache(String cache) {
      if (sharePreference.ifHaveShare(cache)) {
         sharePreference.removeOneCache(cache);
      } else {
         Log.e("this cache", "is not exist.");
      }
   }

}


