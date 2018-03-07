package com.geniuses.newsclient.entity;

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
    private String cityName;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherJson() {
        return weatherJson;
    }

    public void setWeatherJson(String weatherJson) {
        this.weatherJson = weatherJson;
    }

}
