/** 
 * @author LuShuWei E-mail:albertxiaoyu@163.com 
 * @version 创建时间：2014-10-30 下午8:23:29 
 * 类说明 
 */

package com.library_demo.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import com.androidlibrary.activity.CommonTabActivity;
import com.library_demo.*;

public class DemonstrateUseCommontabActivity extends CommonTabActivity {

	private ArrayList<TabItem> items = new ArrayList<TabItem>();
	
	public void OnCreate(Bundle state) {
		super.onCreate(state);
		initalTabItems();
	}
	
	private void initalTabItems()
	{
		Intent oneIntent = new Intent(this,DemonstrateSoapRequest.class);
		Intent twoIntent = new Intent(this,DemonstrateValueUtilUsage.class);
		Intent threeIntent = new Intent(this,DemonstrateCustomDialogActivity.class);
		Intent fourIntent = new Intent(this,DemonstrateUseLibActivity.class);
		
		TabItem one = new TabItem(R.drawable.ic_launcher,"SoapRequest",oneIntent);
		TabItem two = new TabItem(R.drawable.ic_launcher,"ValueUtil",twoIntent);
		TabItem three = new TabItem(R.drawable.ic_launcher,"CustomDialog",threeIntent);
		TabItem four = new TabItem(R.drawable.ic_launcher,"UseLib",fourIntent);
		
		items.add(one);
		items.add(two);
		items.add(three);
		items.add(four);
		
	}

	@Override
	protected ArrayList<TabItem> getTabItems() {
		// TODO Auto-generated method stub
		return items;
	}




}
