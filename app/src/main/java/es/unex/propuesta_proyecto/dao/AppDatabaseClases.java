package es.unex.propuesta_proyecto.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.Usuarios;

/* Base de datos encargada de gestionar las Clases */

@Database(entities = {Clases.class}, version = 3)
public abstract class AppDatabaseClases extends RoomDatabase {

    private static AppDatabaseClases instance;

    public static AppDatabaseClases getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDatabaseClases.class,"Clases.db").build();
        return instance;
    }

    public abstract DaoClases daoClases();
}
