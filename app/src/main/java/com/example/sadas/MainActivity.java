package com.example.sadas;

import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ProgressDialog progressDialog;
	ListView listview;
	MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listview = (ListView)findViewById(1);
        adapter = new MyAdapter(this);
        progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("ÕýÔÚÏÂÔØ....");
		MyAdapter adapter = new MyAdapter(this);
		new MyTask().execute(HttpUtil.BASE_URL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public class MyTask extends AsyncTask<String, Void, List<Map<String,Object>>>{
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		progressDialog.show();
    	}
    	
    	@Override
    	protected void onPostExecute(List<Map<String, Object>> result) {
    		// TODO Auto-generated method stub
    		super.onPostExecute(result);
			adapter.setData(result);
			listview.setAdapter(adapter);
    		adapter.notifyDataSetChanged();
    		progressDialog.dismiss();
    	}

    	@Override
    	protected List<Map<String, Object>> doInBackground(String... params) {
    		// TODO Auto-generated method stub
    		List<Map<String,Object>> list ;
    		
    		String str = HttpUtil.getRequest(params[0]);
    		list = HttpUtil.getRequest2List(str, "products");
    		
    		return list;
    	}
    	
    }
    
}
