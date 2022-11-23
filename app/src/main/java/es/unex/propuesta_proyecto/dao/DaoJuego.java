package es.unex.propuesta_proyecto.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.propuesta_proyecto.model.Armas;

@Dao
public interface DaoJuego {

    @Query("SELECT * FROM Armas")
    abstract List<Armas> obtenerClases();

    @Query("SELECT * FROM Armas WHERE name = :name")
    Armas obtenerClase(String name);

    @Insert
    void insertarClase(Armas...armas);

    @Query("UPDATE Armas set type = :type, subtype = :subtype, accuracy = :accuracy, damage = :damage, range = :range, fire_rate = :fire_rate, mobility = :mobility, control = :control, name = :name WHERE id = :id")
    void actualizarClase(String name,String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, int id);

    @Query("DELETE FROM Armas WHERE id = :id")
    void borrarClase(int id);

}
