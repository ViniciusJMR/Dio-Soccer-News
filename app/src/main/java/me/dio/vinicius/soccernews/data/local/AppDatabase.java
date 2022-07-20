package me.dio.vinicius.soccernews.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.dio.vinicius.soccernews.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
