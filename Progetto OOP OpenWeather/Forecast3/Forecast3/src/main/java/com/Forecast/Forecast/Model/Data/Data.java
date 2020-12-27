package com.Forecast.Forecast.Model.Data;

import org.json.simple.JSONObject;

public interface Data {
  
    	void callApi();
    	void saveFile();
    	void JsonObject(JSONObject obj);
   }


