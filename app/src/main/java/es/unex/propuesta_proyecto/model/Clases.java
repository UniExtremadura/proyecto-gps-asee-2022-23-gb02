package es.unex.propuesta_proyecto.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "Clases")
public class Clases {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String nombre;
    private String usuario;
    private int idArmaPrincipal;
    private int idArmaSecundaria;

    public Clases(String nombre, String usuario, int idArmaPrincipal, int idArmaSecundaria) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.idArmaPrincipal = idArmaPrincipal;
        this.idArmaSecundaria = idArmaSecundaria;
    }

    @Ignore
    public Clases(){
        this.nombre = "";
        this.usuario = "";
        this.idArmaPrincipal = 0;
        this.idArmaSecundaria = 0;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdArmaPrincipal() {
        return idArmaPrincipal;
    }

    public void setIdArmaPrincipal(int idArmaPrincipal) {
        this.idArmaPrincipal = idArmaPrincipal;
    }

    public int getIdArmaSecundaria() {
        return idArmaSecundaria;
    }

    public void setIdArmaSecundaria(int idArmaSecundaria) {
        this.idArmaSecundaria = idArmaSecundaria;
    }

}
