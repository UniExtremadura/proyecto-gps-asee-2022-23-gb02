package es.unex.propuesta_proyecto.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Accesorios")
public class Accesorio {

   public enum TipoAccesorio {
        BOCACHA,
        CAÑON,
        LASER,
        MIRA,
        CULATA,
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private TipoAccesorio tipo;
    private int modPrecision;
    private int modDaño;
    private int modAlcance;
    private int modCadencia;
    private int modMovilidad;
    private int modControl;
    private int idArma;

    public Accesorio(String nombre, TipoAccesorio tipo, int modPrecision, int modDaño, int modAlcance, int modCadencia, int modMovilidad, int modControl, int idArma) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.modPrecision = modPrecision;
        this.modDaño = modDaño;
        this.modAlcance = modAlcance;
        this.modCadencia = modCadencia;
        this.modMovilidad = modMovilidad;
        this.modControl = modControl;
        this.idArma = idArma;
    }

    @Ignore
    public Accesorio() {
        this.nombre = "";
        this.tipo = tipo;
        this.modPrecision = 0;
        this.modDaño = 0;
        this.modAlcance = 0;
        this.modCadencia = 0;
        this.modMovilidad = 0;
        this.modControl = 0;
        this.idArma = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAccesorio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAccesorio tipo) {
        this.tipo = tipo;
    }

    public int getModPrecision() {
        return modPrecision;
    }

    public void setModPrecision(int modPrecision) {
        this.modPrecision = modPrecision;
    }

    public int getModDaño() {
        return modDaño;
    }

    public void setModDaño(int modDaño) {
        this.modDaño = modDaño;
    }

    public int getModAlcance() {
        return modAlcance;
    }

    public void setModAlcance(int modAlcance) {
        this.modAlcance = modAlcance;
    }

    public int getModCadencia() {
        return modCadencia;
    }

    public void setModCadencia(int modCadencia) {
        this.modCadencia = modCadencia;
    }

    public int getModMovilidad() {
        return modMovilidad;
    }

    public void setModMovilidad(int modMovilidad) {
        this.modMovilidad = modMovilidad;
    }

    public int getModControl() {
        return modControl;
    }

    public void setModControl(int modControl) {
        this.modControl = modControl;
    }

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }
}
