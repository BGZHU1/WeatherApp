package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Util.Utils;
import model.Place;
import model.Weather;

//parse jsonObject - feed data into different classes, like place ect... (using Utils class's get/set method)
public class JSONWeatherParser {

   public static Weather getWeather(String data){// the string get from the toString method from weatherHttpClient

       Weather weather=new Weather();

       //create jsonObject from data

       try {
           JSONObject jsonObject=new JSONObject(data);
           //start put data into place object
           Place place = new Place();


           //get and holds the coord data
           //pass in tag and jsonobject as parameters to get coord data in jsonObject
           JSONObject coorObj= Utils.getObject("coord",jsonObject);
           place.setLat(Utils.getFloat("lat",coorObj));
           place.setLon(Utils.getFloat("lon",coorObj));

           //get the sys object : subobject in Jsonobjbect
           JSONObject sysObj=Utils.getObject("sys",jsonObject);
           place.setCountry(Utils.getString("country",sysObj));
           place.setLastupdate((long) Utils.getInt("dt",jsonObject));
           place.setSunrise((long)Utils.getInt("sunrise",sysObj));
           place.setSunset((long)Utils.getInt("sunset",sysObj));
           place.setCity(Utils.getString("name",jsonObject));
           weather.place=place;

           //get the weather info
           JSONArray jsonArray=jsonObject.getJSONArray("weather");
           JSONObject jsonWeather=jsonArray.getJSONObject(0); //get the first object from first element of Jsonarray
           weather.currentCondition.setWeatherId((int) Utils.getInt("id",jsonWeather));
           weather.currentCondition.setDescription(Utils.getString("description",jsonWeather));
           weather.currentCondition.setCondition(Utils.getString("main",jsonWeather));
          // weather.currentCondition.setIcon(Utils.getString("icon",jsonWeather));

           JSONObject mainObj=Utils.getObject("main",jsonObject);
           weather.currentCondition.setHumidity(Utils.getInt("humidity",mainObj));
           weather.currentCondition.setPressure(Utils.getInt("pressure",mainObj));
           weather.currentCondition.setMinTemp(Utils.getFloat("temp_min",mainObj));
           weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max",mainObj));
           weather.currentCondition.setTemperature(Utils.getDouble("temp_max",mainObj));





           //wind - speed/deg
           JSONObject windObj=Utils.getObject("wind",jsonObject);
           weather.wind.setSpeed(Utils.getFloat("speed",windObj));
           weather.wind.setDeg(Utils.getFloat("deg",windObj));

           //clouds
           JSONObject cloudObj=Utils.getObject("clouds",jsonObject);
           weather.clouds.setPrecipitation((int) Utils.getInt("all",cloudObj));

           return weather;


       } catch (JSONException e) {
           e.printStackTrace();
           return null;// in case goes wrong
       }

   }



}
