package es.unex.propuesta_proyecto.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Armas")
public class Armas {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "subtype")
    private String subtype;
    @ColumnInfo(name = "accuracy")
    private int accuracy;
    @ColumnInfo(name = "damage")
    private int damage;
    @ColumnInfo(name = "range")
    private int range;
    @ColumnInfo(name = "fire_rate")
    private int fire_rate;
    @ColumnInfo(name = "mobility")
    private int mobility;
    @ColumnInfo(name = "control")
    private int control;
    @ColumnInfo(name="tipoArma")
    private String tipoArma;
    @ColumnInfo(name="usuario")
    private String usuario;
    @ColumnInfo(name="idClase")
    private int idClase;
    @ColumnInfo(name="principal")
    private int principal;


    public Armas(String name, String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, String tipoArma, String usuario,int idClase, int principal) {
        this.name = name;
        this.type = type;
        this.subtype = subtype;
        this.accuracy = accuracy;
        this.damage = damage;
        this.range = range;
        this.fire_rate = fire_rate;
        this.mobility = mobility;
        this.control = control;
        this.tipoArma=tipoArma;
        this.usuario = usuario;
        this.idClase = idClase;
        this.principal = principal;
    }

    @Ignore
    public Armas() {
        this.name = "";
        this.type = "";
        this.subtype = "";
        this.accuracy = 0;
        this.damage = 0;
        this.range = 0;
        this.fire_rate = 0;
        this.mobility = 0;
        this.control = 0;
        this.tipoArma="";
        this.usuario = "";
        this.idClase = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getFire_rate() {
        return fire_rate;
    }

    public void setFire_rate(int fire_rate) {
        this.fire_rate = fire_rate;
    }

    public int getMobility() {
        return mobility;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }

    public String getUsuario() {return usuario;}

    public void setUsuario(String usuario) {this.usuario = usuario;}

    public int getIdClase() {return idClase;}

    public void setIdClase(int idClase) {this.idClase = idClase;}

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

}
