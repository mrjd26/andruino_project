package com.example.andruino_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.andruino_project.R;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void CallServerOn(View view){
    	new MyAsyncTask().execute(new String[] {"on"});
    }
	
	public void CallServerOff(View view){
    	new MyAsyncTask().execute(new String[] {"off"});
    }
    
    private String convertStreamToString(InputStream is){
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb = new StringBuilder();
    	
    	String line = null;
    	
    	try {
    		while ((line = reader.readLine()) != null) {
    			sb.append(line+"\n");
    		}
    	} catch (IOException e){
    			e.printStackTrace();
    	} finally {
    		try {
    				is.close();
    		} catch (IOException e){
    				e.printStackTrace();
    		}
    	}
    
    	return sb.toString();
    }
    
    
    private class MyAsyncTask extends AsyncTask<String,Void,String>{
    	@Override
    	protected String doInBackground(String...str){
    		HttpClient httpclient = new DefaultHttpClient();
    		
    		Random rand = new Random();
    		
    		int randomNum=0;
    	
    		if((str[0])=="on"){
    		randomNum = (rand.nextInt(10000)+1)*2;
    		}
    		if((str[0])=="off"){
    			randomNum = rand.nextInt((10000)+1);
    			if(randomNum % 2 == 0){
    				randomNum ++;
    			}
    		}
    		
    		
    		HttpGet httpget = new HttpGet("https://myproject0922.appspot.com/andruino?state="+randomNum);
    		
    		try{
    			HttpResponse response = httpclient.execute(httpget);
    			InputStream inputstream = response.getEntity().getContent();
    			String new_response = convertStreamToString(inputstream);
    			return new_response;
    		} catch(Exception e) {
    			return e.getMessage();
    		}
    	}
    	protected void onPostExecute(String result){
    		/*Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();*/
    		/*((TextView)findViewById (R.id.response)).setText(result);*/
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
