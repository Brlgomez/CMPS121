package com.example.brandongomez.cs121asg2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import com.example.brandongomez.cs121asg2.response.QueryResponse;
import java.util.Date;
import java.text.DateFormat;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    String location, elevation, temperature, relativeHum, windSpeed, weather;
    String date = DateFormat.getDateTimeInstance().format(new Date());

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            location = intent.getStringExtra("MyLocIntentKey");
            elevation = intent.getStringExtra("MyEleIntentKey");
            temperature = intent.getStringExtra("MyTempIntentKey");
            relativeHum = intent.getStringExtra("MyRelHumIntentKey");
            windSpeed = intent.getStringExtra("MyWindIntentKey");
            weather = intent.getStringExtra("MyWeatherIntentKey");
            date = intent.getStringExtra("MyDateIntentKey");
        }
        else{
            location = savedInstanceState.getString("MyLocKey");
            elevation = savedInstanceState.getString("MyEleKey");
            temperature = savedInstanceState.getString("MyTempKey");
            relativeHum = savedInstanceState.getString("MyRelHumKey");
            windSpeed = savedInstanceState.getString("MyWindKey");
            weather = savedInstanceState.getString("MyWeatherKey");
            date = savedInstanceState.getString("MyDateKey");
        }
        TextView loc = (TextView) findViewById(R.id.Location);
        TextView ele = (TextView) findViewById(R.id.Elevation);
        TextView temp = (TextView) findViewById(R.id.Temperature);
        TextView relHum = (TextView) findViewById(R.id.RelativeHumidity);
        TextView wind = (TextView) findViewById(R.id.WindSpeed);
        TextView weath = (TextView) findViewById(R.id.Weather);
        TextView time = (TextView) findViewById(R.id.Time);
        loc.setText(location);
        ele.setText(elevation);
        temp.setText(temperature);
        relHum.setText(relativeHum);
        wind.setText(windSpeed);
        weath.setText(weather);
        time.setText(date);
    }
    // the saved instanced if the phone flips
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("MyLocKey", location);
        savedInstanceState.putString("MyEleKey", elevation);
        savedInstanceState.putString("MyTempKey", temperature);
        savedInstanceState.putString("MyRelHumKey", relativeHum);
        savedInstanceState.putString("MyWindKey", windSpeed);
        savedInstanceState.putString("MyWeatherKey", weather);
        savedInstanceState.putString("MyDateKey", date);
        super.onSaveInstanceState(savedInstanceState);
    }
    // get the url information using retrofit
    public void getWeather(View view){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://luca-teaching.appspot.com/weather/default/get_weather/")
                .addConverterFactory(GsonConverterFactory.create())	//parse Gson string
                .client(httpClient)	//add logging
                .build();

        WeatherService service = retrofit.create(WeatherService.class);

        Call<QueryResponse> queryResponseCall = service.getWeather(" ");

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Response<QueryResponse> response) {
                TextView loc = (TextView) findViewById(R.id.Location);
                TextView ele = (TextView) findViewById(R.id.Elevation);
                TextView temp = (TextView) findViewById(R.id.Temperature);
                TextView relHum = (TextView) findViewById(R.id.RelativeHumidity);
                TextView wind = (TextView) findViewById(R.id.WindSpeed);
                TextView weath = (TextView) findViewById(R.id.Weather);
                TextView time = (TextView) findViewById(R.id.Time);
                try {
                    location = "" + response.code();
                    location = "Location: "
                            + response.body().response.conditions.observationLocation.full;
                    elevation = "Elevation: "
                            + response.body().response.conditions.observationLocation.elevation;
                    temperature = "Temperature: "
                            + response.body().response.conditions.tempF + "°F / "
                            + response.body().response.conditions.tempC + "°C";
                    relativeHum = "Relative Humidity: "
                            + response.body().response.conditions.relativeHumidity;
                    windSpeed = "Wind Speed: "
                            + response.body().response.conditions.windMph + " mph / Gust: "
                            + response.body().response.conditions.windGustMph + " mph";
                    weather = "Weather: " + response.body().response.conditions.weather;
                    date = "Updated On: " + DateFormat.getDateTimeInstance().format(new Date());
                    loc.setText(location);
                    ele.setText(elevation);
                    temp.setText(temperature);
                    relHum.setText(relativeHum);
                    wind.setText(windSpeed);
                    weath.setText(weather);
                    time.setText(date);
                }
                catch(Throwable t){
                    loc.setText(getText(R.string.error));
                    ele.setText("");
                    temp.setText("");
                    relHum.setText("");
                    wind.setText("");
                    weath.setText("");
                    time.setText("");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public interface WeatherService {
        @GET("http://luca-teaching.appspot.com/weather/default/get_weather/")
        Call<QueryResponse> getWeather(@Query("Response") String response);
    }

}
