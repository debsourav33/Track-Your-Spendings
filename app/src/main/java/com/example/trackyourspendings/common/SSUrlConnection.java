package com.example.trackyourspendings.common;

import android.os.AsyncTask;
import android.os.HandlerThread;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SSUrlConnection {
    private static final int READ_TIMEOUT= 10000;
    private static final int CONNECT_TIMEOUT= 10000;

    private HttpURLConnection connection;
    private URL url;
    private HashMap<String,String> params= new HashMap<>();
    private DataOutputStream contentStream;
    private BufferedInputStream resultStream;

    private int statusCode;
    private Map<String, List<String>> responseHeaders;

    private String reqPayload= "{" +
            "    \"dsWebAuthToken\": \"+fICOFLmijVxoUHC76E9pMIBT4eglv2nvN8sOhSUMVps3hiM5IRtphZ+uFmyPRKdbgkjUhQMIt4kZlszyEBkxg8Yw0+cY2QzxXSEK2FGEKPNVn3knJEJ/zSaUzAet287fPKZhFRpnHyWt7lTQukjSQNKRXXsugimrJW9IT0bB4t4CAMcWxWGBCcKYlZE3Rny7NCydNgMTksV2ss1PEHh7ILVVcdse463OVTLzsqFYOf5Z4srVEiM/+303MJ38wl9AU8l1hoOyaEJiYHSG3bLhEw3okUDp09XPCcU+Ou/XP4KPVYA3TMrKA8fo948ygfSbZKtL+KyD1d/7ZKGifMmIErAwd0+tFJlsY7paJECVQz8fFJAW1K813jBkKSSV7o489V3KQ2iN5TrcBL1GZNv3HUX5pIOBqp463F8Gk71FZPkm08agcoQ9UbbxcwmMPTy7CDxXlsgIe/FtIJQpNe3FWNg2vKSSjUhBkk9OpdMp77sXCCVp/NxhgSgoZn9ZI//NJWg0V8pvVhJDxANoKWPFnk/LaGGYu50GhB/cj4hDnYmb5zRlB1dvN9fuBhl3egCZRIf+wr1IPDPLD1syrpMkEkGkQ2OtJSAyoZ2IhB+0RXQBJZeuIRmBcclih/LU3rntI8tcowpDNc6RktiPuk3aFYX7O2LnCcrsQnbrAynW4Z2VhPSIuFIGIULmeb6B9boboakk5EAC51+dj0mjg==\"," +
            "    \"accountCountryCode\": \"USA\"," +
            "    \"extended_login\": \"false\"" +
            "}";

    public void request(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                requestInternal();
                return null;
            }
        }.execute();
    }

    public void requestInternal(){
        CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );

        try {
            url= new URL("https://setup.icloud.com/setup/ws/1/accountLogin");
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setUseCaches(false);

            setRequestProperties();

            contentStream= new DataOutputStream(connection.getOutputStream());
            contentStream.write(reqPayload.getBytes("UTF-8"));
            contentStream.flush();
            contentStream.close();

            statusCode= connection.getResponseCode();
            responseHeaders= connection.getHeaderFields();

            if(statusCode>=400 && statusCode<500)
                resultStream= new BufferedInputStream(connection.getErrorStream());
            else
                resultStream= new BufferedInputStream(connection.getInputStream());



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception ee){
            ee.printStackTrace();
        }


    }

    private void setRequestProperties() {
        params.put("Origin","https://www.icloud.com");
        params.put("Referer","https://www.icloud.com");
        params.put("Host","setup.icloud.com");
        params.put("Content-Type","text/plain");

        for(Map.Entry<String,String> entry: params.entrySet()){
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }
    }

}
