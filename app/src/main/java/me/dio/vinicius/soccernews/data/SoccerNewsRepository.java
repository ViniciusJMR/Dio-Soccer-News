package me.dio.vinicius.soccernews.data;

import androidx.room.Room;

import me.dio.vinicius.soccernews.App;
import me.dio.vinicius.soccernews.data.local.AppDatabase;
import me.dio.vinicius.soccernews.data.local.NewsDao;
import me.dio.vinicius.soccernews.data.remote.SoccerNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    private static final String REMOTE_API_URL = "https://viniciusjmr.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME = "soccer-news-db";

    private final SoccerNewsApi remoteApi;
    private final AppDatabase localDb;

    public SoccerNewsApi getRemoteApi() {
        return remoteApi;
    }

    public AppDatabase getLocalDb() {
        return localDb;
    }

    private SoccerNewsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME)
                .allowMainThreadQueries() //FIXME Remover essa gambiarra
                .build();
    }

    public static SoccerNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }
}
