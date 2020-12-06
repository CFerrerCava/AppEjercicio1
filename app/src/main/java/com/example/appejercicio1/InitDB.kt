package com.example.appejercicio1

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class InitDB:SQLiteOpenHelper(appGeneral.CONTEXT,appGeneral.DB_NAME,null,appGeneral.VERSION) {
    val CreateTable = "CREATE TABLE ${appGeneral.TABLENAME} (" +
            "${Clientes.ID} integer primary key autoincrement, " +
            "${Clientes.NOMBRES} text not null, " +
            "${Clientes.APELLIDOS} text not null, " +
            "${Clientes.DIRECCION} text not null, " +
            "${Clientes.TELEFONO} text not null);"
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}