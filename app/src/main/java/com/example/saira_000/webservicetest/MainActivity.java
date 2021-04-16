package com.example.saira_000.webservicetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saira_000.webservicetest.database.RbacDB;
import com.example.saira_000.webservicetest.database.userAccountDBHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    ListView listView;
    Context context;
    ImageView imageView;
    RbacDB rbacDB;
    List<UserModel> userModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = getApplicationContext();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        listView = (ListView) findViewById(R.id.lst);
        imageView = findViewById(R.id.regActivity);

        rbacDB = new RbacDB(context);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserRegister.class);
                startActivity(i);
            }
        });


        setUserData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        setUserData();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public void setUserData()
    {
        userModel = rbacDB.getUser();

        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> firstName = new ArrayList<String>();
        ArrayList<String> lastName = new ArrayList<String>();
        ArrayList<String> email = new ArrayList<String>();
        ArrayList<String> phone = new ArrayList<String>();
        ArrayList<String> password = new ArrayList<String>();

        for(int i=0; i<userModel.size();i++)
        {
            id.add(userModel.get(i).getId());
            firstName.add(userModel.get(i).getFirstname());
            lastName.add(userModel.get(i).getLastname());
            email.add(userModel.get(i).getEmail());
            phone.add(userModel.get(i).getPhonenumber());
            password.add(userModel.get(i).getPassword());
        }

        CustomAdapter adapter = new CustomAdapter(MainActivity.this,id,firstName,lastName,email,phone,password);
        listView.setAdapter(adapter);
    }


}