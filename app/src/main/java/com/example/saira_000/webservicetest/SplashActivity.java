package com.example.saira_000.webservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.saira_000.webservicetest.database.RbacDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static int time = 2000;
    ApiInterface apiInterface;
    RbacDB rbacDB;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rbacDB = new RbacDB(context);

        getUserDatas();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },time);
    }

    public void getUserDatas()
    {
        if(UtilityManager.isConnectingToInternet(context))
        {
            Call<List<UserModel>> callData = apiInterface.getUserData();
            callData.enqueue(new Callback<List<UserModel>>() {
                @Override
                public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                    List<UserModel> output = response.body();

                    rbacDB.insertUser(output);

                }


                @Override
                public void onFailure(Call<List<UserModel>> call, Throwable t) {
                    Toast.makeText( SplashActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

}