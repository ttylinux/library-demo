/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 鍒涘缓鏃堕棿锛�2014-10-30 涓嬪崍8:23:29 绫昏鏄�
 */

package com.library_demo.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.androidlibrary.activity.CommonTabActivity;
import com.library_demo.*;

public class DemonstrateUseCommontabActivity extends CommonTabActivity {

  private ArrayList<TabItem> items = new ArrayList<TabItem>();

  public void OnCreate(Bundle state) {
    super.onCreate(state);

  }

  private void initalTabItems() {
    Intent oneIntent = new Intent(this, DemonstrateSoapRequest.class);
    Intent twoIntent = new Intent(this, DemonstrateValueUtilUsage.class);
    Intent threeIntent = new Intent(this, DemonstrateCustomDialogActivity.class);
    Intent fourIntent = new Intent(this, DemonstrateUseLibActivity.class);
    Intent fiveIntent = new Intent(this, DemonstrateWebViewUsage.class);

    // TabItem one = new TabItem("SoapRequest",oneIntent,"soapRequest");
    // one.setTabBg(R.drawable.tab_bg);
    TabItem two = new TabItem("ValueUtil", twoIntent, "valueUtil");
    two.setTabBg(R.drawable.tab_bg);
    TabItem three = new TabItem("CustomDialog", threeIntent, "customDialog");
    three.setTabBg(R.drawable.tab_bg);
    TabItem four = new TabItem("UseLib", fourIntent, "useLib");
    four.setTabBg(R.drawable.tab_bg);

    TabItem five = new TabItem("WebView", fiveIntent, "WebView");
    five.setTabBg(R.drawable.tab_bg);
    five.setRecreateState(true);

    // items.add(one);
    items.add(two);
    items.add(three);
    items.add(four);
    items.add(five);

  }

  @Override
  protected ArrayList<TabItem> getTabItems() {
    // TODO Auto-generated method stub
    return items;
  }

  @Override
  protected void prepareDatas() {
    // TODO Auto-generated method stub
    initalTabItems();
  }

  @Override
  protected void setTabView(TextView tabView, int position) {
    // TODO Auto-generated method stub
    TabItem one = items.get(position);
    tabView.setText(one.getTabName());
    tabView.setBackgroundResource(one.getTabBg());

  }



}
