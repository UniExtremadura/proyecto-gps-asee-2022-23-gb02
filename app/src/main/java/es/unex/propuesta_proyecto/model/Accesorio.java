package es.unex.propuesta_proyecto.model;

enum TipoAccesorio {
    BOCACHA,
    CAÑON,
    LASER,
    MIRA,
    CULATA,
    ACOPLE,
    MUNICION,
    EMPUÑADURA_TRASERA,
    VENTAJA
}

public class Accesorio {

    String nombre;
    TipoAccesorio tipo;
    Integer modPrecision;
    Integer modDaño;
    Integer modAlcance;
    Integer modCadencia;
    Integer modMovilidad;
    Integer modControl;

    public Accesorio(String nombre, TipoAccesorio tipo, Integer modPrecision, Integer modDaño, Integer modAlcance, Integer modCadencia, Integer modMovilidad, Integer modControl) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.modPrecision = modPrecision;
        this.modDaño = modDaño;
        this.modAlcance = modAlcance;
        this.modCadencia = modCadencia;
        this.modMovilidad = modMovilidad;
        this.modControl = modControl;
    }

    public Accesorio() {
        this.nombre = "";
        this.tipo = tipo;
        this.modPrecision = 0;
        this.modDaño = 0;
        this.modAlcance = 0;
        this.modCadencia = 0;
        this.modMovilidad = 0;
        this.modControl = 0;
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

    public Integer getModPrecision() {
        return modPrecision;
    }

    public void setModPrecision(Integer modPrecision) {
        this.modPrecision = modPrecision;
    }

    public Integer getModDaño() {
        return modDaño;
    }

    public void setModDaño(Integer modDaño) {
        this.modDaño = modDaño;
    }

    public Integer getModAlcance() {
        return modAlcance;
    }

    public void setModAlcance(Integer modAlcance) {
        this.modAlcance = modAlcance;
    }

    public Integer getModCadencia() {
        return modCadencia;
    }

    public void setModCadencia(Integer modCadencia) {
        this.modCadencia = modCadencia;
    }

    public Integer getModMovilidad() {
        return modMovilidad;
    }

    public void setModMovilidad(Integer modMovilidad) {
        this.modMovilidad = modMovilidad;
    }

    public Integer getModControl() {
        return modControl;
    }

    public void setModControl(Integer modControl) {
        this.modControl = modControl;
    }

}
