package com.example.abc.mindvalley_aliasheer_android_test;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.abc.mindvalley_aliasheer_android_test.Database.DatabaseHandler;
import com.example.abc.mindvalley_aliasheer_android_test.Database.Model;
import com.example.abc.mindvalley_aliasheer_android_test.Database.MyAdapter;
import com.example.abc.mindvalley_aliasheer_android_test.Network.ApiClient;
import com.example.abc.mindvalley_aliasheer_android_test.Network.ApiInterface;
import com.example.abc.mindvalley_aliasheer_android_test.Network.checkconnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ali Asheer on 12/11/2016.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;// Declaring Adapter For Recycler View
    LinearLayoutManager mLayoutManager;
    Boolean isInternetPresent = false;
    checkconnection cd;
    String[] PERMISSIONS = {android.Manifest.permission.INTERNET, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    int PERMISSION_ALL = 1;
    //If we want to store json offline
    DatabaseHandler db = new DatabaseHandler(this);
    // List<Model> modelsoffline= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        cd = new checkconnection(this);
        isInternetPresent = cd.isConnectingToInternet();
        mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if(isInternetPresent){
            // Run retrofit service
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<List<Model>> call = apiService.getTopRatedMovies();
            call.enqueue(new Callback<List<Model>>() {
                @Override
                public void onResponse(Call<List<Model>>call, Response<List<Model>> response) {
                    List<Model> models = response.body();
                    mLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                    /*for (int i = 0; i < models.size(); i++) {
                        db.addModel(new Model(models.get(i).getColor()));
                    }*/
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MyAdapter(MainActivity.this, models);
                    mRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onFailure(Call<List<Model>>call, Throwable t) {
                    // Log error here since request failed
                    Log.e("", t.toString());
                }
            });
        } else {
            open(this);
        }
    }

    public void open(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setIcon(R.drawable.cross);
        alertDialogBuilder.setTitle("No Internet");
        alertDialogBuilder.setMessage("It looks like you are not connected to internet?");

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
    //Permissions for higher apis
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
