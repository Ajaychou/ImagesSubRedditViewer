package com.android.imageloader.asynctask;

import android.os.AsyncTask;

import com.android.imageloader.Callbacks.ImageLoaderCallbacks;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Parsing network call using Aysnctask
 */
public class ParseUrlNetwork extends AsyncTask<String, String, String> {
    private ImageLoaderCallbacks imageLoaderCallbacks;

    public ParseUrlNetwork(ImageLoaderCallbacks imageLoaderCallbacks) {
        this.imageLoaderCallbacks = imageLoaderCallbacks;
    }

    @Override
    protected String doInBackground(String... strings) {
        getData(strings[0]);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private void getData(String url_str) {
        URL url;
        StringBuilder result = new StringBuilder();
        HttpURLConnection urlconn = null;
        try {
            url = new URL(url_str);
            urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setReadTimeout(100000);
            urlconn.setConnectTimeout(150000);
            urlconn.setRequestMethod("GET");
            urlconn.connect();

            int code = urlconn.getResponseCode();
            InputStream in = new BufferedInputStream(urlconn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String resp = result.toString();
            imageLoaderCallbacks.onSuccess(resp);
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
            imageLoaderCallbacks.onFailure(e);
        } catch (Exception e) {
            e.printStackTrace();
            imageLoaderCallbacks.onFailure(e);

        } finally {
            if (urlconn != null) {
                urlconn.disconnect();
            }
        }
    }
}
