package my.com.mimagerloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by liuhongwu on 16/1/24.
 * This is the Image Cache
 */
public class ImageCache {
    // this is Image LRU cache
    LruCache<String,Bitmap> bitmapLruCache;

    public ImageCache() {
        initImageCache();
    }

    //init the image cache
    public void initImageCache(){
        //get the max mermory
       final int maxMermory= (int) (Runtime.getRuntime().maxMemory()/1024);
        //get 1/4 the mermory
       final int cacheSize=maxMermory/4;
        bitmapLruCache=new LruCache<String,Bitmap >(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    public  void put(String url,Bitmap bitmap){
        bitmapLruCache.put(url,bitmap);
    }

    public Bitmap get(String url){
        return bitmapLruCache.get(url);
    }
}
