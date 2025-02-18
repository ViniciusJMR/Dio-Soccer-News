package me.dio.vinicius.soccernews.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.dio.vinicius.soccernews.domain.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE favorite = 1")
    LiveData<List<News>> loadFavoriteNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News news);
}
