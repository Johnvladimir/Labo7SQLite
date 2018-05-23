package com.example.john.labo7sqlite.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.john.labo7sqlite.Object.Personas;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_persona";
    public static final String TABLA_USUARIO = "personas";
    public static final String CAMPO_CARNET = "carnet";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_NOTA = "nota";

    public static final String CREAR_TABLA_USUSRIO = "CREATE TABLE "+TABLA_USUARIO+ "(" +CAMPO_CARNET+ " TEXT," +CAMPO_NOMBRE+ " TEXT," +CAMPO_NOTA+ " TEXT)";

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx) {
        if (myDB == null) {
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUSRIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CAMPO_NOMBRE);
        onCreate(db);
    }

    //Agregar persona
    public boolean add(Personas p) {
        ContentValues values = new ContentValues();

        values.put(CAMPO_CARNET, p.getCarnet());
        values.put(CAMPO_NOMBRE, p.getNombre());
        values.put(CAMPO_NOTA, p.getNota());
        db.insert(TABLA_USUARIO, null, values);

        Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();

        return true;
    }

    //Buscar una persona
    public Personas findUser(String carnet) {
        Personas p;

        String[] parametros = {carnet};
        String[] campo = {CAMPO_NOMBRE, CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campo, CAMPO_CARNET + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            p = new Personas(carnet, cursor.getString(0), cursor.getString(1));

        } catch (Exception e) {
            p = null;

        }
        return p;
    }

    //Editar Persona
    public boolean editUser(Personas p) {
        String[] parametros = {p.getCarnet()};
        String[] campo1 = {p.getNombre()};
        String[] campo2 = {CAMPO_NOTA};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOTA, p.getNota());
        db.update(TABLA_USUARIO, values, CAMPO_CARNET + "=?", parametros);
        Toast.makeText(context, "Usuario Actualizado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    //Eliminar persona
    public boolean deleteUser(String carnet) {
        String[] parametros = {carnet};
        db.delete(TABLA_USUARIO, CAMPO_CARNET + "=?", parametros);
        Toast.makeText(context, "Persona Elimnado con exito", Toast.LENGTH_SHORT).show();

        return true;
    }

    //Tomando de la BD
    public ArrayList<Personas> getMostrar(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO, null);
        ArrayList<Personas> per = new ArrayList<>();
        while (cursor.moveToNext()){
            per.add(new Personas(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
        }

        return per;
        }
}