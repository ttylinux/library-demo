/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 创建时间：2014-10-19 下午2:22:37 类说明
 */

package com.library_demo.activity;

import com.androidlibrary.lib.util.NetworkSoapUtil;
import com.library_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?op=getWeatherbyCityName
 * 
 */

public class DemonstrateSoapRequest extends Activity {


  private String nameSpace = "http://WebXml.com.cn/";
  private String serviceUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

  private String methodName = "getWeatherbyCityName";
  private String theCityNameKey = "theCityName";

  private String resultKey = "getWeatherbyCityNameResult";

  private Button btnRequest;
  private Button tidBtnRequest;
  private TextView tvResult;
  private NetworkSoapUtil request;
  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      tvResult.setText(msg.obj + "");
    }
  };

  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.demonstrate_soap_request_layout);
    inital();
  }

  private void inital() {
    btnRequest = (Button) findViewById(R.id.btn_request);
    tvResult = (TextView) findViewById(R.id.tv_result);
    request = new NetworkSoapUtil(nameSpace, serviceUrl);
    btnRequest.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        new Thread() {
          @Override
          public void run() {
            String result =
                request.getWeatherByCityName(theCityNameKey, "深圳", methodName, resultKey);

            Message msg = handler.obtainMessage();
            msg.obj = result;
            handler.sendMessage(msg);

          }

        }.start();
      }
    });



  }

}
