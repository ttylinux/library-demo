/** 
* @author LuShuWei E-mail:albertxiaoyu@163.com 
* @version 创建时间：2014-10-28 下午7:53:08 
* 类说明 
*/ 

package com.library_demo.activity;

import com.library_demo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidlibrary.baidumap.activity.*;

public class DemonstrateUseLibActivity  extends Activity{
	
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.demonstrate_use_lib_activity);
	}

	
	public void startLibActivity(View v)
	{
		Intent intent = new Intent(this,ShowLocationActivity.class);
		intent.putExtra("Values", "something.something");
		startActivity(intent);
	}
}


