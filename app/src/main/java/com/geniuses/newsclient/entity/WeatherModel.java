package com.geniuses.newsclient.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Sch on 2018/2/7.
 */
@Table(name = "WeatherModel")
public class WeatherModel {
    @Column(name = "ID", isId = true, autoGen = true)
    private int id;
    @Column(name = "CITY")
    private String city;
    @Column(name = "WEATHETJSON")
    private String weatherJson;

    public WeatherModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherJson() {
        return weatherJson;
    }

    public void setWeatherJson(String weatherJson) {
        this.weatherJson = weatherJson;
    }

    public String getNowWeather() {
        StringBuffer todayWeather = new StringBuffer();
        if (weatherJson != null) {
            try {
                JSONObject object = new JSONObject(weatherJson);
                JSONObject now = object.getJSONObject("now");
                todayWeather.append(now.getJSONObject("cond").getString("txt") + "----");
                todayWeather.append("当前温度：" + now.getString("tmp") + "℃");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return todayWeather.toString();
    }
}
