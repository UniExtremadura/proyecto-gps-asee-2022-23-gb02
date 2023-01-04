package es.unex.propuesta_proyecto.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.propuesta_proyecto.model.Clases;


@Dao
public interface DaoClases {

    @Query("SELECT * FROM Clases")
    abstract List<Clases> obtenerTodasClases();

    @Query("SELECT * FROM Clases WHERE usuario = :usuario")
    abstract List<Clases> obtenerClasesUsuario(String usuario);

    @Query("SELECT * FROM Clases WHERE nombre = :nombreClase AND usuario = :usuario")
    Clases obtenerClase(String nombreClase, String usuario);

    @Insert
    void insertarClase(Clases clase);

    @Query("DELETE FROM Clases WHERE nombre = :nombre AND usuario = :usuario")
    void borrarClase(String nombre, String usuario);

    @Query("UPDATE Clases SET idArmaPrincipal = :idPrincipal, idArmaSecundaria = :idSecundaria WHERE id = :id")
    void actualizarIdArmas (int idPrincipal, int idSecundaria, int id);

    @Query("UPDATE Clases SET idArmaPrincipal = :idArmaPrincipal, idArmaSecundaria = :idArmaSecundaria WHERE nombre = :nombreClase AND usuario = :usuario")
    void actualizarClase(String nombreClase,String usuario,int idArmaPrincipal, int idArmaSecundaria);
}
