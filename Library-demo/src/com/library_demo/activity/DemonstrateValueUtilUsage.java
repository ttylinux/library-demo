/** 
* @author LuShuWei E-mail:albertxiaoyu@163.com 
* @version 创建时间：2014-10-26 上午11:06:43 
* 类说明 
*/ 

package com.library_demo.activity;

import com.library_demo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.androidlibrary.lib.util.*;

public class DemonstrateValueUtilUsage  extends Activity {
	
	private  TextView valueWithoutDecimal,valueWithTwoDecimal;
	private EditText  valueDecimal,valueMoreDecimal;
	
	
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.demonstrate_valueutil_layout);
		inital();
	}

	 
	private void inital()
	{
		valueWithoutDecimal = (TextView)findViewById(R.id.tv_resultWithOutDecimal);
		valueWithTwoDecimal = (TextView)findViewById(R.id.tv_resultWithTwoDecimal);
		
		valueDecimal = (EditText)findViewById(R.id.edit__hasDecimal);
		valueMoreDecimal=(EditText)findViewById(R.id.edit__morethanTwoDecimal);	
	}
	
	public void getValueWithoutDecimal(View v)
	{
		valueWithoutDecimal.setText(ValueUtil.getValueWithoutDecimal(valueDecimal.getText().toString()));
	}
	
	public void getValueWithTwoDecimal(View v)
	{
		valueWithTwoDecimal.setText(ValueUtil.getValueWithTwoDigit(valueMoreDecimal.getText().toString()));
	}
}


