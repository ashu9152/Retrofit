package com.example.retrofitexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JasonPlaceHolder jasonPlaceHolder = retrofit.create(JasonPlaceHolder.class);

        Call<List<Post>> call = jasonPlaceHolder.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    Log.e("MainActivity","No Response");
                    textViewResult.setText("Code: "+ response.code());
                }

                List<Post> posts = response.body();
                Log.e("MainActivity","Yes Response");
                for (Post post : posts){
                    String content1 ="";
                    content1 += "ID: " +post.getId() + "\n";
                    content1 += "User ID: " +post.getUserId() + "\n";
                    content1 += "Title: " +post.getTitle() + "\n";
                    content1 += "Text: " +post.getText() + "\n\n";

                    textViewResult.append(content1);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("MainActivity","Some Error");
                textViewResult.setText(t.getMessage());
            }
        });
    }
}