package com.metehan.restdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText first_name, last_name;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        button = findViewById(R.id.button);


        ApiService apiService = RetrofitClient.getApiService();

        Actor actor = new Actor();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (first_name.getText().toString().isEmpty() ||
                        last_name.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "Boşlukları doldur!", Toast.LENGTH_SHORT).show();
                } else {
                    actor.setFirst_name(first_name.getText().toString());
                    actor.setLast_name(last_name.getText().toString());

                    Call<String> call = apiService.createActor(actor);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                // Başarılı cevap
                                Log.d("Test123: ", response.body());
                            } else {
                                // Hata durumunda cevap
                                Log.d("Test123: ", response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            // Ağ hatası
                            Log.d("Test123 error: ", t.getMessage());
                        }
                    });

                    Toast.makeText(MainActivity.this, "Butona basıldı!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}