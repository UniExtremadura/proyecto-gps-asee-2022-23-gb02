package es.unex.propuesta_proyecto.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import es.unex.propuesta_proyecto.model.Accesorio;

@Database(entities = {Accesorio.class}, version = 4)
public abstract class AppDatabaseAccesorios extends RoomDatabase {

    private static AppDatabaseAccesorios instance;

    public static AppDatabaseAccesorios getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDatabaseAccesorios.class,"Accesorios.db").build();
        return instance;
    }

    public abstract DaoAccesorios daoAccesorios();

}


