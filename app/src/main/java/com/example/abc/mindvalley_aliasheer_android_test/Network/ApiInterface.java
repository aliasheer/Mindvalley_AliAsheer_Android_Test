package com.example.abc.mindvalley_aliasheer_android_test.Network;

import com.example.abc.mindvalley_aliasheer_android_test.Database.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Ali Asheer on 12/11/2016.
 */

public interface ApiInterface {

    @POST("wgkJgazE")
    Call<List<Model>> getTopRatedMovies();

}