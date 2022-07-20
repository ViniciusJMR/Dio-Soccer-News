package me.dio.vinicius.soccernews.data.remote;

import java.util.List;

import me.dio.vinicius.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {

    @GET("news.json")
    Call<List<News>> getNews();
}
