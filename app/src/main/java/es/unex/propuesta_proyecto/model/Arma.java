package es.unex.propuesta_proyecto.model;

import android.net.Uri;

import java.util.ArrayList;

enum TipoArma {
    FUSIL_DE_ASALTO,
    SUBFUSIL,
    ESCOPETA,
    AMETRALLADORA_LIGERA,
    FUSIL_DE_PRECISION,
    FUSIL_DE_FRANCOTIRADOR,
    PISTOLA,
    LANZAMISILES,
    CUERPO_A_CUERPO
};

public class Arma {

    String nombre;
    Uri imagen;
    TipoArma tipo;
    Integer precision;
    Integer daño;
    Integer alcance;
    Integer cadencia;
    Integer movilidad;
    Integer control;
    ArrayList <Accesorio> accesorios;

    public Arma() {
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

    public TipoArma getTipo() {
        return tipo;
    }

    public void setTipo(TipoArma tipo) {
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

    public ArrayList<Accesorio> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(ArrayList<Accesorio> accesorios) {
        this.accesorios = accesorios;
    }

}
