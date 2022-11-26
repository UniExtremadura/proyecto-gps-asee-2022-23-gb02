package es.unex.propuesta_proyecto.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.propuesta_proyecto.model.Armas;

@Dao
public interface DaoJuego {

    @Query("SELECT * FROM Armas")
    abstract List<Armas> obtenerArmas();

    @Query("SELECT * FROM Armas WHERE id = :idArma")
    Armas obtenerArmaPorId(int idArma);

    @Query("SELECT * FROM Armas WHERE name = :nombreArma AND usuario = :nombreUsuario")
    Armas obtenerArmaUsuario(String nombreArma, String nombreUsuario);

    @Query("SELECT * FROM Armas WHERE usuario = :nombreUsuario")
    abstract List<Armas> obtenerArmasPorNombreUsuario(String nombreUsuario);

    @Query("SELECT * FROM Armas WHERE idClase = :idClase and usuario = :nombreUsuario")
    abstract List<Armas> obtenerArmasPorClaseyNombre(int idClase, String nombreUsuario);

    @Insert
    void insertarArmas(Armas...armas);

    @Query("UPDATE Armas set name = :name, type = :type, subtype = :subtype, accuracy = :accuracy, damage = :damage, range = :range, fire_rate = :fire_rate, mobility = :mobility, control = :control, principal =:principal  WHERE id = :id AND idClase = :idClase")
    void actualizarArma(String name, String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, int id, int idClase, int principal);

    @Query("DELETE FROM Armas WHERE id = :id")
    void borrarArma(int id);

}
