/** 
* @author LuShuWei E-mail:albertxiaoyu@163.com 
* @version 创建时间：2014-10-19 下午2:22:37 
* 类说明 
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

public class DemonstrateSoapRequest extends Activity{
	
	
	private String _nameSpace="http://WebXml.com.cn/";
	private String _serviceUrl="http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
	
	private String _methodName="getWeatherbyCityName";
	private String _theCityNameKey="theCityName";
	
	private String _resultKey="getWeatherbyCityNameResult";
	
	private Button _btn_request;
	private Button _tidBtn_request;
	private TextView _tv_result;
	private NetworkSoapUtil _request;
	private Handler _handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			_tv_result.setText(msg.obj+"");
		}
	};
	
	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.demonstrate_soap_request_layout);
		inital();
	}
	
	private void inital()
	{
		_btn_request = (Button)findViewById(R.id.btn_request);
		_tv_result = (TextView)findViewById(R.id.tv_result);
		_request = new NetworkSoapUtil(_nameSpace,_serviceUrl);
		_btn_request.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread()
				{
					@Override
					public void run()
					{
						String result = _request.getWeatherByCityName(_theCityNameKey, "深圳", _methodName, _resultKey);
					
						Message msg = _handler.obtainMessage();
						msg.obj = result;
					    _handler.sendMessage(msg);
						
					}
					
				}.start();
			}
		});
		

		
	}

}


