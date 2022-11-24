package es.unex.propuesta_proyecto.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Usuarios;

@Dao
public interface DaoUsuarios {

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