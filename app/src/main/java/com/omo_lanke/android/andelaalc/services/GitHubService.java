package com.omo_lanke.android.andelaalc.services;

import com.omo_lanke.android.andelaalc.models.GitHubProjects;
import com.omo_lanke.android.andelaalc.models.GitHubResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by omo_lanke on 06/03/2017.
 */
//https://api.github.com/users/${username}/repos
public interface GitHubService {

    @GET("/search/users?q=repos:>0+location:Lagos")
    Call<GitHubResponse> listUsers();

    @GET("/users/{username}/repos")
    Call<GitHubProjects[]> findRepos(@Path("username") String username);
}