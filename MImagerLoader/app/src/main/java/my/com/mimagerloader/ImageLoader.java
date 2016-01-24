package my.com.mimagerloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuhongwu on 16/1/24.
 *  图片加载类
 */
public class ImageLoader {
    //Iamge cache
    //thread pool  this thread number is cpu number
    ExecutorService  mExeService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //image cache
   InterImageCache mImageCache= new MemoryCache();
    //inject cache
    public void setImageCache(InterImageCache cache){
        mImageCache=cache;
    }

  public  void displayImage(String url,ImageView imageview){
      Bitmap bitmap=mImageCache.get(url);
      if (bitmap==null){
          imageview.setImageBitmap(bitmap);
          return;
      }
      //imageCache is null submit thread pool download

  }
    private  void submitLoadRequest(final String url,final  ImageView imageView){
        //set the image tag
        imageView.setTag(url);
        mExeService.submit(new Thread(){
            @Override
            public void run() {
                Bitmap bitmap=downloadImage(url);
                if (bitmap==null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });

    }
    public Bitmap downloadImage(String imgUrl){
        Bitmap bitmap=null;
        try {
            URL url=new URL(imgUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            bitmap= BitmapFactory.decodeStream(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bitmap;
    }
}
