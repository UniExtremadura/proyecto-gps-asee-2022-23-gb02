package es.unex.propuesta_proyecto.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.RepoArmas;


@Dao
public interface DaoJuego {

    @Query("SELECT * FROM Armas")
    abstract List<Armas> obtenerArmas();

    //Pruebas
    @Query("SELECT * FROM Armas")
    abstract LiveData<List<Armas>> getAllLD();

    @Query("SELECT * FROM Armas WHERE id = :idArma")
    Armas obtenerArmaPorId(int idArma);

    @Query("SELECT * FROM Armas WHERE name = :nombreArma AND usuario = :nombreUsuario")
    Armas obtenerArmaUsuario(String nombreArma, String nombreUsuario);

    @Query("SELECT * FROM Armas WHERE usuario = :nombreUsuario")
    abstract List<Armas> obtenerArmasPorNombreUsuario(String nombreUsuario);

    @Query("SELECT * FROM Armas WHERE usuario = :nombreUsuario")
    abstract  LiveData<List<RepoArmas>> obtenerArmasPorNombreUsuarioRep(String nombreUsuario);

    @Query("SELECT COUNT(*) FROM Armas WHERE usuario = :usuario")
    int getNumberArmasUsuario ( String usuario);
    @Insert
    void insertarArmas(Armas...armas);

    @Query("UPDATE Armas set name = :name, type = :type, subtype = :subtype, accuracy = :accuracy, damage = :damage, range = :range, fire_rate = :fire_rate, mobility = :mobility, control = :control, principal =:principal  WHERE id = :id AND idClase = :idClase")
    void actualizarArma(String name, String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, int id, int idClase, int principal);

    @Query("UPDATE Armas set name = :name, type = :type, subtype = :subtype, accuracy = :accuracy, damage = :damage, range = :range, fire_rate = :fire_rate, mobility = :mobility, control = :control, principal =:principal  WHERE id = :id")
    void actualizarArmaPorId(String name, String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, int id, int principal);

    @Query("DELETE FROM Armas WHERE id = :id")
    void borrarArma(int id);

    @Query("SELECT id FROM Armas WHERE idClase = :idClase AND principal = :principal")
    int getIdArmaTipo(int idClase, int principal);

    @Query("DELETE FROM Armas WHERE id = :id AND usuario = :nombreusuario")
    void borrarArmaPorNombreyId(int id, String nombreusuario);

    @Query("DELETE FROM Armas WHERE usuario = :nombreusuario AND idClase = :clase AND principal = :principal")
    void borrarArmasUsuario(int clase, String nombreusuario, int principal);

    @Query("DELETE FROM Armas WHERE idClase = :idClase AND usuario = :nombreUsuario")
    void borrarArmaPorClaseyNombre(int idClase, String nombreUsuario);

}
