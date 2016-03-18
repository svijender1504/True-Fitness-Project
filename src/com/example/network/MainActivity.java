package com.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Networking().execute("");
	}
	
	class Networking extends AsyncTask<String, String, String>
	{

		StringBuilder strBuilder=new StringBuilder();
		//String param[]={"a","b","c"};
		int i,n;
		JSONArray jsonArray;
		String name;
		
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL	("http://api.androidhive.info/contacts/");
			HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
			InputStream inputStream=httpURLConnection.getInputStream();
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
			String read;
			while((read=bufferedReader.readLine())!=null)
			{
			strBuilder.append(read);	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		for(i=0;i<3;i++)
//		{
//	
//		publishProgress(param[i]);
//		}
//		return param[i];
		
		
		try {
			JSONObject jsonObject=new JSONObject("strBuilder");
			jsonArray = new JSONArray("strBuilder");
			jsonArray=jsonObject.getJSONArray("name");
			n=jsonArray.length();
			//jsonArray.getJSONArray("name");
			
			JSONObject object=jsonArray.getJSONObject(0);
			 name=object.getString("name");
			//Toast.makeText(getApplicationContext(), name, 3000).show();
			
//			for(i=0;i<n;i++)
//			{
//				name[i];
//			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
			
			return strBuilder.toString();
			
			
			
			
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			TextView txtjson;
			txtjson=(TextView)findViewById(R.id.txtjson);
			Toast.makeText(getApplicationContext(), result+" post called", 3000).show();
			txtjson.setText(result);
			
			Toast.makeText(getApplicationContext(), n, 3000).show();
			
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
