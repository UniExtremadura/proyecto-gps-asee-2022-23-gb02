package es.unex.propuesta_proyecto.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Clases {

    @PrimaryKey
    @NonNull
    private String name;
    private String type;
    private String subtype;
    private int accuracy;
    private int damage;
    private int range;
    private int fire_rate;
    private int mobility;
    private int control;
    private String prons;
    private String cons;

    public Clases(@NonNull String name, String type, String subtype, int accuracy, int damage, int range, int fire_rate, int mobility, int control, String prons, String cons) {
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

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
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
