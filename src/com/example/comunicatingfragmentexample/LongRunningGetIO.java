package com.example.comunicatingfragmentexample;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.os.AsyncTask;

class LongRunningGetIO extends AsyncTask<Void, Void, String> {
	
	public AsyncResponse delegate=null;
	
	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {

		
		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);

			if (n > 0)
				out.append(new String(b, 0, n));
		}

		return out.toString();
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(
				"http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=kke3abdzttj3smvvhb8xm83u&q=Toy+Story&page_limit=3");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		return text;
	}
	

	public void onPostExecute(String results) {
		
		if (results != null) {

			delegate.processFinish(results);
			
		}
	}

}
