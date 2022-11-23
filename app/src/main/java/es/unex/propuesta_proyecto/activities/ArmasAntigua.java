package es.unex.propuesta_proyecto.activities;

import android.net.Uri;

enum Tipo {
    FUSIL_DE_ASALTO,
    SUBFUSIL,
    ESCOPETA,
    AMETRALLADORA_LIGERA,
    FUSIL_DE_FRANCOTIRADOR,
    PISTOLA,
    CUERPO_A_CUERPO
};

public class ArmasAntigua {

    String nombre;
    Uri imagen;
    Tipo tipo;
    Integer precision;
    Integer daño;
    Integer alcance;
    Integer cadencia;
    Integer movilidad;
    Integer control;

    public ArmasAntigua() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Uri getImagen() {
        return imagen;
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getDaño() {
        return daño;
    }

    public void setDaño(Integer daño) {
        this.daño = daño;
    }

    public Integer getAlcance() {
        return alcance;
    }

    public void setAlcance(Integer alcance) {
        this.alcance = alcance;
    }

    public Integer getCadencia() {
        return cadencia;
    }

    public void setCadencia(Integer cadencia) {
        this.cadencia = cadencia;
    }

    public Integer getMovilidad() {
        return movilidad;
    }

    public void setMovilidad(Integer movilidad) {
        this.movilidad = movilidad;
    }

    public Integer getControl() {
        return control;
    }

    public void setControl(Integer control) {
        this.control = control;
    }


}
