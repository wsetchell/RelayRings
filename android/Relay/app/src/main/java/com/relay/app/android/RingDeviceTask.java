package com.relay.app.android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Make a device ring.
 */
public class RingDeviceTask extends AsyncTask<Void, Void, Void> {
  private static final String TAG = "RingDeviceTask";
  private static final String RINGER_ENDPOINT = "willComputer.local:8000";

  @Override
  protected Void doInBackground(Void... voids) {

    Log.i(TAG, "fetching: '" + ip + "'");
    try {
      fetchUrl(RINGER_ENDPOINT);
    } catch (IOException e) {
      Log.e(TAG, "Could not load : " + ip);
    }
    return null;
  }

  @Override
  protected void onPostExecute(Void v) {
  }

  private String fetchUrl(String urlStr) throws IOException {
    InputStream is = null;
    String content;
    try {
      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setReadTimeout(10000 /* milliseconds */);
      conn.setConnectTimeout(15000 /* milliseconds */);
      conn.setRequestMethod("GET");
      conn.setDoInput(true);
      // Starts the query
      conn.connect();
      int response = conn.getResponseCode();
      Log.d(TAG, "The response is: " + response);
      is = conn.getInputStream();

      // Convert the InputStream into a string
      content = streamToString(is);
      return contentAsString;

      // Makes sure that the InputStream is closed after the app is
      // finished using it.
    } finally {
      if (is != null) {
        is.close();
      }
    }
    return content;
  }

  private String streamToString(InputStream stream) throws IOException, UnsupportedEncodingException {
    BufferedReader r = new BufferedReader(new InputStreamReader(stream));
    StringBuilder total = new StringBuilder();
    String line;
    while ((line = r.readLine()) != null) {
      total.append(line);
    }
    return total.toString();
  }

}
