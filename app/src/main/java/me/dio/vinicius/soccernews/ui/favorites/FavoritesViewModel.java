package me.dio.vinicius.soccernews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import me.dio.vinicius.soccernews.data.SoccerNewsRepository;
import me.dio.vinicius.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {
    }

    public LiveData<List<News>> loadFavoriteNews() {
        final LiveData<List<News>> news;
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news){
        AsyncTask.execute(() ->
                SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }

}