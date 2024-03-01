package com.example.sunnyweather.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sunnyweather.SunnyWeatherApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilBitmap {
   /**
    * @param image 控件
    * @param bitmap 需要载入的图片
    * @param defoIcon 默认载入的图片
    * @param isZoomByImageView 是否需要根据控件大小对图片进行缩放
    */
   public static void setImageBitmap(Context context, ImageView image, Bitmap bitmap, int defoIcon, boolean isZoomByImageView){

      if (image == null)
         return;

      if (bitmap == null)
         bitmap = BitmapFactory.decodeResource(context.getResources(), defoIcon);

      if (isZoomByImageView) {

         ViewGroup.LayoutParams params = image.getLayoutParams();

         float scaleWidth = ((float) params.width) / bitmap.getWidth();
         float scaleHeight = ((float) params.height) / bitmap.getHeight();

         Matrix matrix = new Matrix();
         matrix.postScale(scaleWidth, scaleHeight);

         image.setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
      }
      else{

         image.setImageBitmap(bitmap);
      }

   }

   /**
    * 通过url加载Bitmap,inSampleSize进行计算设置
    *
    * @作者
    * @创建时间
    * @param load_url
    * @return Bitmap
    */
   public static Bitmap getloadlBitmap(String load_url, int width, int height) {

      Bitmap bitmap = null;

      if (!UtilText.isEmpty(load_url)) {

         File file = new File(load_url);
         if (file.exists()) {
            FileInputStream fs = null;
            try {
               fs = new FileInputStream(file);
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            }

            if (null != fs) {
               try {
                  BitmapFactory.Options opts = new BitmapFactory.Options();
                  opts.inJustDecodeBounds = true;
                  BitmapFactory.decodeFileDescriptor(fs.getFD(), null, opts);
                  opts.inDither = false;
                  opts.inPurgeable = true;
                  opts.inInputShareable = true;
                  opts.inTempStorage = new byte[32 * 1024];
                  opts.inSampleSize = calculateInSampleSize(opts,DensityUtils.dpToPx(SunnyWeatherApplication.context, width),DensityUtils.dpToPx(SunnyWeatherApplication.context, height)
                          );
                  opts.inJustDecodeBounds = false;

                  bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(),
                          null, opts);
               } catch (IOException e) {
                  e.printStackTrace();
               } finally {
                  if (null != fs) {
                     try {
                        fs.close();
                     } catch (IOException e) {
                        e.printStackTrace();
                     }
                  }
               }
            }
         }
      }

      return bitmap;
   }

   public static int calculateInSampleSize(BitmapFactory.Options options,
                                           int reqWidth, int reqHeight) {
      // Raw height and width of image
      final int height = options.outHeight;
      final int width = options.outWidth;
      int inSampleSize = 1;

      if (height > reqHeight || width > reqWidth) {

         // Calculate ratios of height and width to requested height and
         // width
         final int heightRatio = Math.round((float) height
                 / (float) reqHeight);
         final int widthRatio = Math.round((float) width / (float) reqWidth);

         // Choose the smallest ratio as inSampleSize value, this will
         // guarantee
         // a final image with both dimensions larger than or equal to the
         // requested height and width.
         inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
      }

      return inSampleSize;
   }


}
