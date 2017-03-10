package com.omo_lanke.android.andelaalc.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omo_lanke.android.andelaalc.models.GitHubResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omo_lanke on 08/03/2017.
 */

public class Api {

    private GitHubService gitHubService;

    public Api(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public GitHubService gitHubService(){
        return gitHubService;
    }
}