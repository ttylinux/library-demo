
package com.library_demo.activity;

import com.androidlibrary.common.view.CustomDialog;
import com.library_demo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DemonstrateCustomDialogActivity extends Activity{
	
	
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.demonstrate_custom_dialog);
	}
	
	
	public void showDialog(View  v)
	{
		final CustomDialog dialog = new CustomDialog(this,R.layout.custom_dialog);
		dialog.show();
		
		Button ok = (Button)dialog.findViewById(R.id.btn_ok);
		Button cancle = (Button)dialog.findViewById(R.id.btn_cancle);
		
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(DemonstrateCustomDialogActivity.this, "Click OK Button", Toast.LENGTH_SHORT).show();
			}
		});
		
		cancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		
	}
	
}