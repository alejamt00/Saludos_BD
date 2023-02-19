package com.example.saludos_bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Clase BaseDatosHelper, que extiende de SQLiteOpenHelper, necesario para usar y crear la base de datos
 */
public class BaseDatosHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "saludos";
    private static final int DATABASE_VERSION = 1;

    public BaseDatosHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(EstructuraBBDD.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
