package my.com.mimagerloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liuhongwu on 16/1/24.
 * this is DiskCache
 */
public class DiskCache  implements InterImageCache{
    static  String cacheDir="sdcard/cache/";

    //get cache image
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(url);
    }

    //set image to disk cache
    public  void put(String url,Bitmap bitmap){
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
