package com.example.saira_000.webservicetest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("finaldata.php")
    Call<List<UserModel>> getUserData();

    @FormUrlEncoded
    @POST("webservice.php")
    Call<List<UserModel>> register(@Field("module") String module, @Field("firstname") String firstname, @Field("lastname") String lastname, @Field("email") String email, @Field("phonenumber") String phonenumber, @Field("password") String password);
}
