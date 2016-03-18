package com.example.brandongomez.cs121asg3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.example.brandongomez.cs121asg3.response.QueryResponse;
import com.example.brandongomez.cs121asg3.response.ResultList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public class Chat extends AppCompatActivity{

    String lat, lon, user_id, nickname, message, message_id;
    float lo, la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        /*
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linlay);
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textView1.setText("programmatically created TextView1");
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
        linearLayout.addView(textView1);
        */

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            lat = extras.getString("lat");
            lon = extras.getString("lon");
            user_id = extras.getString("user_id");
            nickname = extras.getString("nickname");
        } else {
            lat = (String) savedInstanceState.getSerializable("lat");
            lon = (String) savedInstanceState.getSerializable("lon");
            user_id = (String) savedInstanceState.getSerializable("user_id");
            nickname = (String) savedInstanceState.getSerializable("nickname");
        }
        la = Float.parseFloat(lat);
        lo = Float.parseFloat(lon);
        getChat(findViewById(R.id.refresh));
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("lat", lat);
        savedInstanceState.putString("lon", lon);
        savedInstanceState.putString("user_id", user_id);
        savedInstanceState.putString("nickname", nickname);
    }
    // get the url information using retrofit

    public void getChat(View view){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://luca-teaching.appspot.com/localmessages/default/")
                .addConverterFactory(GsonConverterFactory.create())	//parse Gson string
                .client(httpClient)	//add logging
                .build();

        getChatService service = retrofit.create(getChatService.class);
        Call<QueryResponse> queryResponseCall = service.getChat(la, lo, user_id);

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Response<QueryResponse> response) {
                //create a new text view
                List<ResultList> result;
                result = getResult(response.body());
                TextView messages = (TextView) findViewById(R.id.postedMessages);
                String allMessages = "";
                //TextView[] textViewArray = new TextView[result.size()];
                try {
                    for (int i = result.size() - 1; i >= 0; i--) {
                        //textViewArray[i] = new TextView();
                        if(!result.get(i).getUserId().equals(user_id)){
                            allMessages += result.get(i).getMessage();
                            allMessages += "\n";
                            allMessages += result.get(i).getNickname();
                            allMessages += "\n";
                            allMessages += result.get(i).getTimestamp();
                            allMessages += ("\n" + "\n");
                        }
                        else{
                            allMessages += ("                                        " + result.get(i).getMessage());
                            allMessages += "\n";
                            allMessages += ("                                        " + result.get(i).getNickname());
                            allMessages += "\n";
                            allMessages += ("                                        " + result.get(i).getTimestamp());
                            allMessages += ("\n" + "\n");
                        }
                    }
                    messages.setText(allMessages);
                } catch (Throwable t) {
                    messages.setText("An error has occurred, please try again or send an email to brlgomez@ucsc.edu");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed

            }

            public List<ResultList> getResult(QueryResponse response) {
                List<ResultList> venues;
                venues = response.getResultList();
                return venues;
            }
        });
    }

    public void postChat(View view){
        EditText postedMessage = (EditText) findViewById(R.id.editMessage);
        message = postedMessage.getText().toString();
        SecureRandomString srs = new SecureRandomString();
        message_id = srs.nextString();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://luca-teaching.appspot.com/localmessages/default/")
                .addConverterFactory(GsonConverterFactory.create())	//parse Gson string
                .client(httpClient)	//add logging
                .build();

        postChatService service = retrofit.create(postChatService.class);
        Call<QueryResponse> queryResponseCall = service.postChat(la, lo, user_id, nickname, message, message_id);

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Response<QueryResponse> response) {
                //create a new text view
                List<ResultList> result;
                result = getResult(response.body());
                TextView messages = (TextView) findViewById(R.id.postedMessages);
                String allMessages = "";
                String old = messages.getText().toString();
                try {
                    allMessages += ("                                        " + message + "\n");
                    allMessages += ("                                        " + nickname + "\n");
                    allMessages += ("                                        Now" + "\n" + "\n");
                    messages.setText(old + allMessages);
                } catch (Throwable t) {
                    messages.setText("An error has occurred, please try again or contact brlgomez@ucsc.edu");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed

            }

            public List<ResultList> getResult(QueryResponse response) {
                List<ResultList> venues;
                venues = response.getResultList();
                return venues;
            }
        });
        postedMessage.setText("");
    }

    public interface getChatService {
        @GET("get_messages")
        Call<QueryResponse> getChat(@Query("lat") float lat,
                                    @Query("lng") float lon,
                                    @Query("user_id") String user);
    }

    public interface postChatService {
        @GET("post_message")
        Call<QueryResponse> postChat(@Query("lat") float lat,
                                     @Query("lng") float lon,
                                     @Query("user_id") String user,
                                     @Query("nickname") String nickname,
                                     @Query("message") String message,
                                     @Query("message_id") String message_id);
    }
}
