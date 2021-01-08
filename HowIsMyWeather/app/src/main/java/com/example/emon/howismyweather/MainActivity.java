package com.example.emon.howismyweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView cityText;
    private TextView description;
    private TextView temp;
    private TextView pressure;
    private TextView windspeed;
    private TextView windDeg;

    private TextView humidity;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String city ="London";

        cityText = (TextView) findViewById(R.id.cityText);
        description = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        humidity = (TextView) findViewById(R.id.hum);
        pressure = (TextView) findViewById(R.id.press);
        windspeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
        imageView = (ImageView) findViewById(R.id.condIcon);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});

    }

    private class JSONWeatherTask extends AsyncTask<String,Void,Weather>{

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            WeatherHTTPclient weatherHTTPclient = new WeatherHTTPclient();
            String data = weatherHTTPclient.getWeatherData(params[0]);

            try {
                weather = JSONWeatherParser.getWeather(data);
                //let's retrieve the icon
                weather.icondata = weatherHTTPclient.getImage(weather.currentCondition.getIcon());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if(weather.icondata !=null && weather.icondata.length>0){
                Bitmap img = BitmapFactory.decodeByteArray(weather.icondata,0,weather.icondata.length);
                imageView.setImageBitmap(img);
            }

            cityText.setText(weather.location.getCity()+" , "+weather.location.getCountry());
            description.setText(weather.currentCondition.getCondition()+"("+weather.currentCondition.getDescription()+")");
            temp.setText(""+Math.round(weather.temparature.getTemp() - 273.15)+"°C");
            humidity.setText(""+weather.currentCondition.getHumidity()+"%");
            pressure.setText(""+weather.currentCondition.getPressure()+"hPa");
            windspeed.setText(""+weather.wind.getSpeed()+"mps");
            windDeg.setText(""+weather.wind.getDegree()+"°");
        }
    }
}
