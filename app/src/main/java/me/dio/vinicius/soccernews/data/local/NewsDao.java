package me.dio.vinicius.soccernews.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.dio.vinicius.soccernews.domain.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE favorite = :favorite")
    List<News> loadFavoriteNews(boolean favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News news);
}
