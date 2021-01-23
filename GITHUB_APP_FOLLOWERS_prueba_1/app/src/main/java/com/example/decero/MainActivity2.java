package com.example.decero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.decero.Models.User;
import com.example.decero.Service.APIService;
import com.example.decero.Service.AdapterDatos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private ImageView perfil;
    private Context context;
    private TextView userInfo;
    private TextView reposNum;
    private TextView followersNum;
    private APIService apiService;

    private List<User> lista;
    private RecyclerView recycler;
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView)findViewById(R.id.textView);
        perfil = (ImageView)findViewById(R.id.imageView);
        userInfo = (TextView)findViewById(R.id.info);
        recycler = (RecyclerView)findViewById(R.id.infoUsers);
        followersNum = (TextView)findViewById(R.id.followersNum);
        reposNum = (TextView)findViewById(R.id.reposNum);


        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        textView.setText("Username: " + username);

        context = getApplicationContext();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(APIService.class);
        //getUser(username);
        getFollowers(username);

        //CARGANDO!!!!

        loadingDialog = new LoadingDialog(MainActivity2.this);
        loadingDialog.startLoadingDialog();




    }

    public void getFollowers(String username){


        Call<List<User>> call = apiService.getFollow(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){

                    lista = response.body();
                    //followersNum.setText(lista.size());
                    recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                    AdapterDatos adapter = new AdapterDatos(lista);
                    recycler.setAdapter(adapter);

                    //desaparecer carga
                    loadingDialog.dismissDialog();

                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.e("failure",t.getLocalizedMessage());
                Intent intent = new Intent(getApplicationContext(),ErrorActivity.class);
                startActivity(intent);
            }
        });


    }

    /*public void getUser(String username){

        Call<User> call = apiService.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()){

                    User user = response.body();

                    reposNum.setText(user.getPublic_repos());
                    followersNum.setText(user.getFollowers());

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e("failure",t.getLocalizedMessage());

            }
        });

    }*/

}