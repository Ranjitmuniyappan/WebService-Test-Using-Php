package com.example.saira_000.webservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saira_000.webservicetest.Constant.Iconstant;
import com.example.saira_000.webservicetest.database.RbacDB;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegister extends AppCompatActivity {

    private static final String TAG = "UserRegister";

    ApiInterface apiInterface;
    RbacDB rbacDB;


    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phoneNumber;
    EditText password;
    Button register;
    Context context;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        context = UserRegister.this;

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rbacDB = new RbacDB(context);

        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phonenumber);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();
                String Email = email.getText().toString();
                String pnumber = phoneNumber.getText().toString();
                String pass = password.getText().toString();

                putRegister(fname,lname,Email,pnumber,pass);
                getUserDatas();

                startActivity(new Intent(UserRegister.this, MainActivity.class));
            }
        });

    }

    public void putRegister(final String fname,final String lname,final String email,final String pnumber,final String password)
    {

        if(UtilityManager.isConnectingToInternet(context))
        {
            Call<List<UserModel>> call = apiInterface.register(Iconstant.register, fname, lname, email, pnumber, password);
            call.enqueue(new Callback<List<UserModel>>() {
                @Override
                public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                    Log.d(TAG, "onResponse : " + response.body());

                }

                @Override
                public void onFailure(Call<List<UserModel>> call, Throwable t) {
                    Log.d(TAG, "onResponse : Somthing went wrong!");
                }
            });
        }else {

            Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(UserRegister.this, MainActivity.class));
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
                    Toast.makeText( UserRegister.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}