package com.library_demo;


import java.util.Arrays;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private ListView _listView;
	

	private String[] demos = {"DownloadManager Demo","Make a Request, which is based on Soap protocol","ValueUtil"};
	private String[] classNames = {"com.library_demo.activity.DownloadManagerDemo","com.library_demo.activity.DemonstrateSoapRequest","com.library_demo.activity.DemonstrateValueUtilUsage"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_listView = (ListView)findViewById(R.id.demoList);
		
		LinkedList<String> items = new LinkedList<String>();
		items.addAll(Arrays.asList(demos));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
		_listView.setAdapter(adapter);
		_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				try {
					startOneActivity(Class.forName(classNames[position]));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(MainActivity.this, ""+classNames[position], Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		
		
	}

	
	
	
	private void startOneActivity(Class<?>  cls)
	{
		Intent intent = new Intent(MainActivity.this,cls);
		startActivity(intent);
	}
	
}
