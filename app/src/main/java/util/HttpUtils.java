package util;

import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by ex-zhongwencheng001 on 16/11/8.
 */
public class HttpUtils {
    public static void sendHttpRequest(final String address,
                                       final HttpCallbackListener listener) {
      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  HttpClient httpClient = new DefaultHttpClient();
                  HttpGet httpGet = new HttpGet(address);
                  HttpResponse httpResponse = httpClient.execute(httpGet);
                  if (httpResponse.getStatusLine().getStatusCode() == 200) {
                      // 请求和响应都成功了
                      HttpEntity entity = httpResponse.getEntity();
                      String response = EntityUtils.toString(entity,"utf-8");
                      if (listener != null) {
                    // 回调onFinish()方法
                          Log.e("sendHttpRequest:", response);
                       listener.onFinish(response);
                      }
                  }
              } catch (Exception e) {

                  if (listener != null) {
                      // 回调onError()方法
                      listener.onError(e);

                  }
                  e.printStackTrace();
              }
          }
      }).start();



    }


    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }
}
