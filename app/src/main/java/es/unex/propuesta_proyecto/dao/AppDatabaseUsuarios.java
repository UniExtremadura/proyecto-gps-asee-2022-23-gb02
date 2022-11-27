package es.unex.propuesta_proyecto.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import es.unex.propuesta_proyecto.model.Usuarios;

/* Base de datos encargada de gestionar los Usuarios */

@Database(entities = {Usuarios.class}, version = 2)
public abstract class AppDatabaseUsuarios extends RoomDatabase {

    private static AppDatabaseUsuarios instance;

    public static AppDatabaseUsuarios getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDatabaseUsuarios.class,"Usuarios.db").build();
        return instance;
    }
    public abstract DaoUsuarios daoUsuarios();
}
