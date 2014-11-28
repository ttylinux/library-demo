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

  private Button btnDownload;
  private ProgressDialog progressDialog;
  private DownloadManagerUtil downloadUtil;
  private DownloadStatusObserver observer;
  private Long downloadId;
  private DownloadResultReceiver receiver;

  private String filePath;

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {

      HashMap<String, Integer> one = (HashMap<String, Integer>) msg.obj;
      int cur = one.get(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
      int max = one.get(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
      int status = one.get(DownloadManager.COLUMN_STATUS);

      if (status == DownloadManager.STATUS_RUNNING && max > 0) {
        progressDialog.setMax(max);
        progressDialog.setProgress(cur);
        progressDialog.show();
      } else if (status == DownloadManager.STATUS_SUCCESSFUL) {
        progressDialog.dismiss();
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
    if (receiver != null) {
      try {

        unregisterReceiver(receiver);
      } catch (Exception e) {

        Log.e(TAG, "Already unregister receiver !!");
      }

    }
  }



  private void inital() {

    btnDownload = (Button) findViewById(R.id.Btn_download);
    progressDialog = new ProgressDialog(this);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setTitle("正在下载...");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);

    filePath = getSdSavePath();
    downloadUtil =
        new DownloadManagerUtil((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE));
    btnDownload.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        downloadId =
            downloadUtil.startDownload(HttpConstants.DownloadUrl, Uri.fromFile(new File(filePath)));

        observer = new DownloadStatusObserver(handler);
        DownloadManagerDemo.this.getContentResolver().registerContentObserver(
            LibConstant.DownloadInfoDataBase_ContentUri, true, observer);
        receiver = new DownloadResultReceiver();
        DownloadManagerDemo.this.registerReceiver(receiver, new IntentFilter(
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

      Message msg = handler.obtainMessage();
      msg.obj = downloadUtil.getCurDownloadInfo(downloadId);
      handler.sendMessage(msg);
    }

  }

  private class DownloadResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      // TODO Auto-generated method stub
      if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
        HashMap<String, Object> one = downloadUtil.getCurDownloadInfo(downloadId);
        String filePath = (String) one.get(DownloadManager.COLUMN_LOCAL_FILENAME);

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        startActivity(i);
      }
    }

  }
}
