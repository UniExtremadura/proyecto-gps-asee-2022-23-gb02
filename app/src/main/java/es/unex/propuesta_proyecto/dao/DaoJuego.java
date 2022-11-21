package es.unex.propuesta_proyecto.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.propuesta_proyecto.model.Clases;

@Dao
public interface DaoJuego {

    @Query("SELECT * FROM Clases")
    abstract List<Clases> obtenerClases();

    @Query("SELECT * FROM Clases WHERE name = :name")
    Clases obtenerClase(String name);

    @Insert
    void insertarClase(Clases...clases);

    @Query("UPDATE Clases set type = :type, subtype = :subtype, accuracy = :accuracy, damage = :damage, range = :range, fire_rate = :fire_rate, mobility = :mobility, control = :control, prons = :prons, cons = :cons WHERE name = :name")
    void actualizarClase(String name,String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, String prons, String cons);

    @Query("DELETE FROM Clases WHERE name = :name")
    void borrarClase(String name);

}
