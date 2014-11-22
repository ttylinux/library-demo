/*
 * @author LuShuWei E-mail:albertxiaoyu@163.com ����ʱ�� 2014-10-20
 */

package com.library_demo.activity;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.androidlibrary.lib.util.AsyncHttpClientUtil;
import com.library_demo.HttpConstants;
import com.library_demo.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DemonstrateAsyncHttpClient extends Activity {

  private EditText authName;
  private EditText password;
  private Button btnRequestWithoutAuth;
  private Button btnRequestAuth;
  private TextView result;

  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.asynchttp_layout);
    ButterKnife.inject(this);
    inital();
  }

  private void inital() {

    result = (TextView) findViewById(R.id.tv_result);
    authName = (EditText) findViewById(R.id.edit_authName);
    password = (EditText) findViewById(R.id.edit_authPassword);
    btnRequestWithoutAuth = (Button) findViewById(R.id.btn_requestWithoutAuth);
    btnRequestAuth = (Button) findViewById(R.id.btn_requestWithAuth);
    btnRequestAuth.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View arg0) {
        // TODO Auto-generated method stub

        String userName = authName.getText().toString();
        String strPassword = password.getText().toString();
        if (!userName.equals("") && !strPassword.equals("")) {
          AsyncHttpClientUtil.getClient().setBasicAuth(userName, strPassword);

          AsyncHttpClientUtil.get(HttpConstants.TopAllUrl, null, new AsyncHttpResponseHandler() {

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
              // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
              // TODO Auto-generated method stub
              try {
                result.setText(new String(arg2, HttpConstants.UTFCharSet));
              } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }

            }
          });
        } else {
          Toast.makeText(DemonstrateAsyncHttpClient.this,
              "Please input authUserName and authPassword", Toast.LENGTH_SHORT).show();
        }

      }
    });

    btnRequestWithoutAuth.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View arg0) {
        // TODO Auto-generated method stub
        AsyncHttpClientUtil.get(HttpConstants.BaiduUrl, null, new AsyncHttpResponseHandler() {

          @Override
          public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            // TODO Auto-generated method stub

          }

          @Override
          public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            // TODO Auto-generated method stub
            try {
              result.setText(new String(arg2, HttpConstants.UTFCharSet));
            } catch (UnsupportedEncodingException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        });
      }
    });

  }

}
