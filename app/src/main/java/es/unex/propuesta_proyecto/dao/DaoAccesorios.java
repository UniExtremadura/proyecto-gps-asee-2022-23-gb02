package es.unex.propuesta_proyecto.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.propuesta_proyecto.model.Accesorio;
import es.unex.propuesta_proyecto.model.Armas;

@Dao
public interface DaoAccesorios {

    @Query("SELECT * FROM Accesorios")
    abstract List<Accesorio> obtenerTodosAccesorios();

    @Query("SELECT * FROM Accesorios WHERE idArma = :idArma")
    Accesorio obtenerAccesoriosUsuario(int idArma);

    @Insert
    void insertarAccesorio(Accesorio accesorio);

    @Update
    void actualizarAccesorio(Accesorio accesorio);

    @Query("DELETE FROM Accesorios WHERE idArma = :idArma")
    void borrarAccesorio(int idArma);

}
