package Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

/**
 * This class helps to get specific data from JSONobject by searching through the tags
 * in the JSONObject after JSONWeatherParser parse the object
 */

public class Utils {
    //all the data try to get or fetch
    public static final String Base_URL="http://api.openweathermap.org/data/2.5/weather?q=";
    //public static final String ICON_URL="http://api.openweathermap.org/img/w/";

    public static JSONObject getObject(String tagName,JSONObject jsonObject)throws JSONException{
        JSONObject jObj=jsonObject.getJSONObject(tagName);
        return jObj;
    }

    public static String getString(String tagName,JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName,JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }

    public static double getDouble(String tagName,JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }

    public static float getInt(String tagName,JSONObject jsonObject) throws JSONException{
        return (int) jsonObject.getDouble(tagName);
    }



}
