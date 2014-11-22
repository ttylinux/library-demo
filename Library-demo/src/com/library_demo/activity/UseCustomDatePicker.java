package com.library_demo.activity;

import com.androidlibrary.common.view.CustomDatePickerDialog;
import com.androidlibrary.common.view.DatePickerListener;
import com.library_demo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UseCustomDatePicker extends Activity implements DatePickerListener {



  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.use_custom_dialog);


  }

  public void show(View v) {

    CustomDatePickerDialog dialog = new CustomDatePickerDialog(this, this);
    dialog.init();

  }

  @Override
  public void onDateChangeListener(int year, int month, int dayOfmonth) {
    // TODO Auto-generated method stub
    Toast
        .makeText(this, "select date:" + year + "-" + month + "-" + dayOfmonth, Toast.LENGTH_SHORT)
        .show();
  }

}
