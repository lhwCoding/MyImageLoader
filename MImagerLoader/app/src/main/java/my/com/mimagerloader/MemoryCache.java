package my.com.mimagerloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by liuhongwu on 16/1/24.
 * Image memory Cache
 */
public class MemoryCache implements InterImageCache {
    private LruCache<String,Bitmap> memoryCache;
    /**
     * init LUR cache
     */
    public MemoryCache() {
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return  memoryCache.get(url);
    }
}
