package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public static boolean handleProvinceResponse(String response){
        try {

            if (!TextUtils.isEmpty(response)) {
                JSONArray allProvince = new JSONArray();
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;

            }
        }
            catch (JSONException e) {
                e.printStackTrace();

            }

        return false;
    }

    public static boolean handleCityResponse(String response,int provinceId){
        try {
            if (!TextUtils.isEmpty(response)) {
                JSONArray allCity=new JSONArray();
                for (int i=0;i<allCity.length();i++) {
                    JSONObject cityObject = allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int cityId){
        try{
            if(!TextUtils.isEmpty(response)){
                JSONArray allCounty=new JSONArray();
                for(int i=0;i<allCounty.length();i++){
                    JSONObject countyObject=allCounty.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
