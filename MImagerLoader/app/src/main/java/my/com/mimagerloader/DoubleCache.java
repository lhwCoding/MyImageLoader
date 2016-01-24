package my.com.mimagerloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by liuhongwu on 16/1/24.
 * this Second Image cache
 */
public class DoubleCache {
    MemoryCache imageCache=new MemoryCache();
    DiskCache diskCache=new DiskCache();

    //first get mermory image cache,is null to  get the SD card cache
    public Bitmap get(String url){
        Bitmap bitmap=imageCache.get(url);
        if (bitmap==null){
            bitmap=diskCache.get(url);
        }
        return  bitmap;
    }

    //set image cache to mermory and SD card
    public  void  put(String url,Bitmap bitmap){
        imageCache.put(url,bitmap);
        diskCache.put(url,bitmap);
    }

}
