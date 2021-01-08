package com.example.emon.howismyweather;

/**
 * Created by Emon on 7/8/2017.
 */

public class Weather {
    public Location location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temparature temparature = new Temparature();
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

    public byte[] icondata;
    /**
     * Created by Emon on 7/8/2017.
     */

    public static class CurrentCondition {

        private int weatherID;
        private String condition;
        private String description;
        private String icon;

        private float pressure;
        private float humidity;

        public int getWeatherID() {
            return weatherID;
        }

        public void setWeatherID(int weatherID) {
            this.weatherID = weatherID;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public class Temparature{
        private float temp;
        private float minTemp;
        private float maxTemp;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }
    }

    public class Wind{
        private float speed;
        private float degree;

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getDegree() {
            return degree;
        }

        public void setDegree(float degree) {
            this.degree = degree;
        }
    }

    public class Rain{
        private String time;
        private float amount;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }

    public class Snow{
        private String time;
        private float amount;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }

    public class Clouds{
        private int perc;

        public int getPerc() {
            return perc;
        }

        public void setPerc(int perc) {
            this.perc = perc;
        }
    }
}
