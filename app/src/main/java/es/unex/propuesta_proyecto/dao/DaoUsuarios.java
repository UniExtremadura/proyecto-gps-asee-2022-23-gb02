package es.unex.propuesta_proyecto.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import es.unex.propuesta_proyecto.model.Usuarios;


@Dao
public interface DaoUsuarios {

    /* Para las pruebas */
    @Query("SELECT * FROM Usuarios")
    abstract LiveData<List<Usuarios>> obtenerUsuariosLV();

    @Query("SELECT * FROM Usuarios")
    abstract List<Usuarios> obtenerUsuarios();

    @Query("SELECT * FROM Usuarios WHERE name = :name")
    Usuarios comprobarUsuario(String name);

    @Insert
    void insertarUsuario(Usuarios usuario);

    @Delete
    void borrarUsuario(Usuarios usuario);

    @Query("UPDATE Usuarios SET password = :pass WHERE name = :nombre")
    void actualizarContrasena(String nombre,String pass);

}
