package com.avanade.santander.contato.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Cell")
public final class Cell {

    @PrimaryKey
    @NonNull
    @Expose
    private final int id;

    @Expose
    private final int type;

    @Nullable
    @Expose
    private final String message;

    @Expose
    private final int typefield;

    @Expose
    private final boolean hidden;

    @Expose
    private final double topSpacing;

    @Expose
    private final int show;

    @Expose
    private final boolean required;


    public Cell(@NonNull int id, int type, @Nullable String message, int typefield, boolean hidden, double topSpacing, int show, boolean required) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.typefield = typefield;
        this.hidden = hidden;
        this.topSpacing = topSpacing;
        this.show = show;
        this.required = required;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public int getTypefield() {
        return typefield;
    }

    public boolean isHidden() {
        return hidden;
    }

    public double getTopSpacing() {
        return topSpacing;
    }

    public int getShow() {
        return show;
    }

    public boolean isRequired() {
        return required;
    }
}

