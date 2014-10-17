/*
 *  @author LuShuWei  E-mail:albertxiaoyu@163.com
 *  创建时间 2014-10-17
 */

package com.library_demo.activity;

import java.io.File;
import java.util.HashMap;

import com.androidlibrary.lib.util.DownloadManagerUtil;

import com.library_demo.ConfigConstant;
import com.library_demo.HttpConstants;
import com.library_demo.R;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class DownloadManagerDemo extends Activity {

	private Button _btn_download;
	private ProgressDialog _progressDialog;
	private DownloadManagerUtil _downloadUtil;
	private DownloadHandler _handler;

	private Long _downloadId;

	public void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.download_manager_layout);

		_handler = new DownloadHandler();
		_downloadUtil = new DownloadManagerUtil(
				(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE));
		_downloadId = _downloadUtil.startDownload(HttpConstants.DownloadUrl,
				getSdSavePath());

	}

	private String getSdSavePath() {
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(ConfigConstant.DownloadFileDirName),
				ConfigConstant.YcbApkName);
		return file.toString();
	}

	class DownloadHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {

		}
	}

	private class DownloadStatusObserver extends ContentObserver {

		public DownloadStatusObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onChange(boolean selfChange) {

			Message msg = _handler.obtainMessage();
			msg.obj = _downloadUtil.getCurDownloadInfo(_downloadId);
			_handler.sendMessage(msg);
		}

	}

}
