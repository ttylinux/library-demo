/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 创建时间：2014-11-22 下午1:23:01 类说明
 */

package com.library_demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.library_demo.R;

public class ActivityWithScrollableTextView extends Activity {


  private Button setValueBtn;
  private TextView scrollableTextView;
  private EditText inputEdit;

  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_with_scrollable_textview);
    inital();

  }

  private void inital() {
    setValueBtn = (Button) findViewById(R.id.btnSet);
    scrollableTextView = (TextView) findViewById(R.id.scrollableTextView);
    inputEdit = (EditText) findViewById(R.id.input_edit);

    setValueBtn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        scrollableTextView.setText(inputEdit.getText().toString());

      }
    });

  }

}
