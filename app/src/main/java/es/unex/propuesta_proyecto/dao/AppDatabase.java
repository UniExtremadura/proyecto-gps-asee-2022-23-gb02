package es.unex.propuesta_proyecto.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.unex.propuesta_proyecto.model.Clases;

@Database(
        entities = {Clases.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoJuego daoJuego();
}
