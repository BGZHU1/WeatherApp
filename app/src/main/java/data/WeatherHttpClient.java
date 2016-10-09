package data;
// This file is to get the JSONobject for JSONWeahterParser to use

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;


public class WeatherHttpClient {

    public String getWeatherData(String place) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        //http and URL Connection - connection between the app and web(API)
        //Base_URL pass in to fetch data (append the place)
        try {
            connection = (HttpURLConnection) (new URL(Utils.Base_URL + place)).openConnection();
            //get
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            //Read the response
            StringBuffer stringBuffer=new StringBuffer();
            inputStream=connection.getInputStream();//get the string that retruns to app - in bit format

            //read the response in a understanding way : put in string into buffer - and able to read in string format
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line=null;

            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}