package me.dio.vinicius.soccernews;

import android.app.Application;


/**
 * FIXME instancia do contexto centralizada mesmo que seja um "anti-pattern"
 *  Alterar problema no futuro utilizando a ideia de singletons através do Hilt ou Dagger
 */
public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
}
