package com.library_demo.activity;

import java.io.File;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.core.AsyncTask;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import com.androidlibrary.lib.util.FileUtil;
import com.library_demo.HttpConstants;
import com.library_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
			_handler.stop();		
		} else {
			Toast.makeText(this, "Have not start yet.", Toast.LENGTH_SHORT)
					.show();
		}

	}

	public void startDownload(View v) {
		FileUtil util = new FileUtil(ShowBreakPointTransmitionActivity.this);
		File saveDir;
		File newFile = null;
		try {
			saveDir = util.getAnPublicExternalStorageDir("BreakPointExample",
					Environment.DIRECTORY_DOWNLOADS);
			newFile = new File(saveDir.getAbsolutePath(), "ycb.apk");
			if(!(newFile.canWrite()&&newFile.exists()))
			{
				throw new Exception("save file is not ready.");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
		_handler = _fh.download(HttpConstants.DownloadUrl,
				newFile.getAbsolutePath(), true, new AjaxCallBack<File>() {

					@Override
					public void onLoading(long count, long current) {

						_tv_process.setText("ÏÂÔØ½ø¶È£º" + current + "/" + count);
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
