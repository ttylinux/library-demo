/*
 * @author LuShuWei E-mail:albertxiaoyu@163.com ����ʱ�� 2014-10-17
 */

package com.library_demo.activity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.androidlibrary.lib.LibConstant;
import com.androidlibrary.lib.util.DownloadManagerUtil;

import com.library_demo.ConfigConstant;
import com.library_demo.HttpConstants;
import com.library_demo.R;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DownloadManagerDemo extends Activity {

  private static final String TAG = "DownloadManager";

  private Button _btn_download;
  private ProgressDialog _progressDialog;
  private DownloadManagerUtil _downloadUtil;
  private DownloadStatusObserver observer;
  private Long _downloadId;
  private DownloadResultReceiver _receiver;

  private String filePath;

  private Handler _handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {

      HashMap<String, Integer> one = (HashMap<String, Integer>) msg.obj;
      int cur = one.get(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
      int max = one.get(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
      int status = one.get(DownloadManager.COLUMN_STATUS);

      if (status == DownloadManager.STATUS_RUNNING && max > 0) {
        _progressDialog.setMax(max);
        _progressDialog.setProgress(cur);
        _progressDialog.show();
      } else if (status == DownloadManager.STATUS_SUCCESSFUL) {
        _progressDialog.dismiss();
        Toast.makeText(DownloadManagerDemo.this, "下载成功，文件保存路径:" + filePath, Toast.LENGTH_SHORT)
            .show();

      }

    }
  };

  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.download_manager_layout);
    inital();
  }

  @Override
  public void onPause() {
    super.onPause();
    if (_receiver != null) {
      try {

        unregisterReceiver(_receiver);
      } catch (Exception e) {

        Log.e(TAG, "Already unregister receiver !!");
      }

    }
  }



  private void inital() {

    _btn_download = (Button) findViewById(R.id.Btn_download);
    _progressDialog = new ProgressDialog(this);
    _progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    _progressDialog.setTitle("正在下载...");
    _progressDialog.setCancelable(false);
    _progressDialog.setCanceledOnTouchOutside(false);

    filePath = getSdSavePath();
    _downloadUtil =
        new DownloadManagerUtil((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE));
    _btn_download.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        _downloadId =
            _downloadUtil.startDownload(HttpConstants.DownloadUrl, Uri.fromFile(new File(filePath)));

        observer = new DownloadStatusObserver(_handler);
        DownloadManagerDemo.this.getContentResolver().registerContentObserver(
            LibConstant.DownloadInfoDataBase_ContentUri, true, observer);
        _receiver = new DownloadResultReceiver();
        DownloadManagerDemo.this.registerReceiver(_receiver, new IntentFilter(
            DownloadManager.ACTION_DOWNLOAD_COMPLETE));

      }
    });

  }

  private String getSdSavePath() {
    File file =
        new File(Environment.getExternalStoragePublicDirectory(ConfigConstant.DownloadFileDirName),
            ConfigConstant.YcbApkName);
    File dir = file.getParentFile();
    if (!dir.exists())
      dir.mkdirs();

    if (!file.exists())
      try {
        file.createNewFile();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    return file.toString();
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

  private class DownloadResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      // TODO Auto-generated method stub
      if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
        HashMap<String, Object> one = _downloadUtil.getCurDownloadInfo(_downloadId);
        String filePath = (String) one.get(DownloadManager.COLUMN_LOCAL_FILENAME);

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        startActivity(i);
      }
    }

  }
}
