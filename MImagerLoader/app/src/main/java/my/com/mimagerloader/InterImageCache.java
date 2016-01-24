package my.com.mimagerloader;

import android.graphics.Bitmap;

/**
 * Created by liuhongwu on 16/1/24.
 */
public interface InterImageCache {
    public void put (String url,Bitmap bitmap);
    public  Bitmap get(String url);
}
