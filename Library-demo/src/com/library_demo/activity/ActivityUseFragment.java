/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 创建时间：2014-10-28 下午9:04:14 类说明
 */

package com.library_demo.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidlibrary.baidumap.fragment.ShowLocationInMapFragment;
import com.library_demo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ActivityUseFragment extends Activity {

  private int times;

  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.demonstrate_use_fragment);
  }

  public void refresh(View v) {
    JSONArray array = new JSONArray();
    JSONObject one = new JSONObject();
    try {
      times++;
      one.put("Name", "Albert");
      one.put("time", times);

      array.put(one);
      ShowLocationInMapFragment f = ShowLocationInMapFragment.newInstance(array);
      getFragmentManager().beginTransaction().replace(R.id.fragment_area, f).commit();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
