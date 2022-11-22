package es.unex.propuesta_proyecto.model;

import android.util.Log;
import android.widget.TextView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;

@Entity()
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
    @ColumnInfo(name = "prons")
    private String prons;
    @ColumnInfo(name = "cons")
    private String cons;

    public Armas(String name, String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, String prons, String cons) {
        this.name = name;
        this.type = type;
        this.subtype = subtype;
        this.accuracy = accuracy;
        this.damage = damage;
        this.range = range;
        this.fire_rate = fire_rate;
        this.mobility = mobility;
        this.control = control;
        this.prons = prons;
        this.cons = cons;
    }
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
        this.prons = "";
        this.cons = "";
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

    public String getProns() {
        return prons;
    }

    public void setProns(String prons) {
        this.prons = prons;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }
}
