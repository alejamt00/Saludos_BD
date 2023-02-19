package com.example.saludos_bd;

public class EstructuraBBDD {

    public static final String TABLE_DATOS_PERSONALES = "usuarios";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_NAME="nombre";
    public static final String COLUMN_PASS="password";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBBDD.TABLE_DATOS_PERSONALES + " (" +
                    EstructuraBBDD.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    EstructuraBBDD.COLUMN_NAME + " TEXT," +
                    EstructuraBBDD.COLUMN_PASS + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBBDD.TABLE_DATOS_PERSONALES;

    public static final String[] projection = {
            EstructuraBBDD.COLUMN_ID,
            EstructuraBBDD.COLUMN_NAME,
            EstructuraBBDD.COLUMN_PASS};

    public static final String selection =
            EstructuraBBDD.COLUMN_NAME+" = ? AND " + EstructuraBBDD.COLUMN_PASS+ " = ?";

    public static final String[] selectionArgs = {"Nombre"};




}
