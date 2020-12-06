package com.example.appejercicio1

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class appGeneral:Application() {
    companion object{
        lateinit var CONTEXT:Context
        lateinit var DB: InitDB
        var DB_NAME ="BDVentas"
        var VERSION = 1
        var TABLENAME = "Clientes"
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        DB = InitDB()
    }
}