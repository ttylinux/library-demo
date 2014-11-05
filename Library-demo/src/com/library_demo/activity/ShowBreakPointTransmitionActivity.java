package com.library_demo.activity;

import java.io.File;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import com.library_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowBreakPointTransmitionActivity extends Activity {

	private TextView _tv_process;
	private FinalHttp _fh;
	private HttpHandler _handler;

	public void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.breakpoint_transmition_layout);
		inital();
	}

	private void inital() {
		_tv_process = (TextView) findViewById(R.id.tv_process);
		_fh = new FinalHttp();

	}

	public void stopDownload(View v) {
		if (_handler != null) {
			Toast.makeText(this, "Have not start yet.", Toast.LENGTH_SHORT)
					.show();
		} else {
			_handler.stop();
		}

	}

	public void startDownload(View v) {
		_handler = _fh.download("url", "savePath", true,
				new AjaxCallBack<File>() {

					@Override
					public void onLoading(long count, long current) {
					};

					@Override
					public void onSuccess(File t) {

					};

					@Override
					public void onFailure(Throwable t, int errorNo,
							String strMsg) {

					};

				}.progress(true, 3));
	}
}