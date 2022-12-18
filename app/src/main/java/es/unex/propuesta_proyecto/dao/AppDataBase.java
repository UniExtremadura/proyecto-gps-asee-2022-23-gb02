package es.unex.propuesta_proyecto.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.propuesta_proyecto.model.Accesorio;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.Usuarios;


@Database(entities = {Armas.class, Accesorio.class, Usuarios.class, Clases.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;

    public static AppDataBase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDataBase.class,"Datos.db").build();
        return instance;
    }

    public abstract DaoJuego daoJuego();
    public abstract DaoAccesorios daoAccesorios();
    public abstract DaoUsuarios daoUsuarios();
    public abstract DaoClases daoClases();
}