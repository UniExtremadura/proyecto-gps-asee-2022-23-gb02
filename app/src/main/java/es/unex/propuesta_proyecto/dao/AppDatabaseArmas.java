package es.unex.propuesta_proyecto.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.propuesta_proyecto.model.Armas;

@Database(entities = {Armas.class}, version = 1)
public abstract class AppDatabaseArmas extends RoomDatabase {
    private static AppDatabaseArmas instance;

    public static AppDatabaseArmas getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDatabaseArmas.class,"Armas.db").build();
        return instance;
    }

    public abstract DaoJuego daoJuego();
}
