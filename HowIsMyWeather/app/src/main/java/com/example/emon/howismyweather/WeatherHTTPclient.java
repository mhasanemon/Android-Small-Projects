package com.example.emon.howismyweather;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Emon on 7/8/2017.
 */

public class WeatherHTTPclient {
    //private static String Base_URL = "http://api.openweathermap.org/data/2.5/weather?q=" ;
    private static String Base_URL = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";

    private static String Image_URL = "http://openweathermap.org/img/w/";
    public String getWeatherData(String location){
        HttpURLConnection connection = null;
        URL url = null;
        InputStream is = null;

        try {
            //url = new URL(Base_URL+location);
            url = new URL(Base_URL);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = bufferedReader.readLine())!=null){
                buffer.append(line+"\r\n");
            }

            is.close();
            connection.disconnect();
            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.disconnect();
            }catch (Throwable t){

            }
        }

        return null;
    }

    public byte[] getImage(String code){
        HttpURLConnection connection = null;
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(Image_URL+code+".png");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Let's read response
            is  = connection.getInputStream();
            byte[] buffer = new byte[4048];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while (is.read(buffer)!=-1){
                byteArrayOutputStream.write(buffer);
            }

            return byteArrayOutputStream.toByteArray();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.disconnect();
            }catch (Throwable t){

            }
        }

        return null;
    }
}
