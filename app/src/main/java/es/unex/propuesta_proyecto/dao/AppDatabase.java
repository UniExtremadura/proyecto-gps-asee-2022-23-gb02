package es.unex.propuesta_proyecto.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.unex.propuesta_proyecto.model.Armas;

@Database(
        entities = {Armas.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoJuego daoJuego();
}
