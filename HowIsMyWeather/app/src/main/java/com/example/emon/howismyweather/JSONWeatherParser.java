package com.example.emon.howismyweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emon on 7/8/2017.
 */

public class JSONWeatherParser {
    
    //data is the string format of the whole xml file
    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();
        
        //We create Json object from the data
        JSONObject object = new JSONObject(data);
        
        Location loc = new Location();
        
        //we start extracting the location info
        JSONObject cordinate = getObject("coord",object);

        loc.setLatitude(getFloat("lat",cordinate));
        loc.setLongitude(getFloat("lon",cordinate));

        //setting attributes
        JSONObject sysObject = getObject("sys",object);

        loc.setCountry(getString("country",sysObject));
        loc.setSunrise(getInt("sunrise",sysObject));
        loc.setSunset(getInt("sunset",sysObject));
        loc.setCity(getString("city",sysObject));
        weather.location = loc;


        //we get the weather info by an array
        JSONArray jsonArray = object.getJSONArray("weather");

        //we use only first value of the array
        JSONObject JSONWeather = jsonArray.getJSONObject(0);

        //set the information of the weather
        weather.currentCondition.setWeatherID(getInt("id",JSONWeather));

        weather.currentCondition.setDescription(getString("description",JSONWeather));
        weather.currentCondition.setCondition(getString("main",JSONWeather));
        weather.currentCondition.setIcon(getString("icon",JSONWeather));


        //getting humidity ,pressure,temparature from "main"
        JSONObject condition = getObject("main",object);

        weather.currentCondition.setHumidity(getInt("humidity",condition));
        weather.currentCondition.setPressure(getInt("pressure",condition));
        weather.temparature.setMaxTemp(getFloat("temp_max",condition));
        weather.temparature.setMinTemp(getFloat("temp_min",condition));
        weather.temparature.setTemp(getFloat("temp",condition));


        //Wind
        JSONObject windObject = getObject("wind",object);

        weather.wind.setSpeed(getFloat("speed",windObject));
        weather.wind.setDegree(getFloat("deg",windObject));

        //Clouds
        JSONObject cloudObject = getObject("clouds",object);

        weather.clouds.setPerc(getInt("all",cloudObject));

        return weather;
    }
    
    //returns jason object with desired tagname
    private static JSONObject getObject(String tagName,JSONObject object) throws JSONException {
        JSONObject subject = object.getJSONObject(tagName);
        return subject;
    }

    //returns String with desired tagname
    private static String getString(String tagName,JSONObject object) throws JSONException {
        String subject = object.getString(tagName);
        return subject;
    }

    //returns Float with desired tagname
    private static float getFloat(String tagName,JSONObject object) throws JSONException {
        float subject = (float) object.getDouble(tagName);
        return subject;
    }

    //returns int with desired tagname
    private static int getInt(String tagName,JSONObject object) throws JSONException {
        int subject = object.getInt(tagName);
        return subject;
    }

}
