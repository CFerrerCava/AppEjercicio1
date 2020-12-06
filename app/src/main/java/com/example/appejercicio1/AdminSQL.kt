package com.example.appejercicio1

import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast


data class Cliente(
        var id: Int,
        var nombres: String,
        var apellidos: String,
        var direccion: String,
        var telefono: String
)
class AdminSQL{
    fun  ObtenerTodos():ArrayList<Cliente>?{
        try {
            val nombres= arrayListOf<Cliente>()
            val db = appGeneral.DB.readableDatabase
            val numDatos = DatabaseUtils.queryNumEntries(db, appGeneral.TABLENAME).toInt()
            if (numDatos>0){
                val qry = "SELECT ${Clientes.ID},${Clientes.NOMBRES},${Clientes.APELLIDOS},${Clientes.DIRECCION},${Clientes.TELEFONO} FROM ${appGeneral.TABLENAME};"
                val c = db.rawQuery(qry, null)
                c.moveToFirst()
                do {
                    nombres.add(
                            Cliente(
                                    c.getInt(0),
                                    c.getString(1),
                                    c.getString(2),
                                    c.getString(3),
                                    c.getString(
                                            4
                                    )
                            )
                    )
                }while (c.moveToNext())
            }else{
                Toast.makeText(appGeneral.CONTEXT, "No hay ningún registro", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return nombres
        }catch (ex: Exception){
            Toast.makeText(appGeneral.CONTEXT, "No se puede listar", Toast.LENGTH_SHORT).show()
            return null
        }
    }
    fun  ObtenerCliente(id:Int):Cliente?{
        try {
            val db = appGeneral.DB.readableDatabase
            val numDatos = DatabaseUtils.queryNumEntries(db, appGeneral.TABLENAME).toInt()
            if (numDatos>0){
                val qry = "SELECT ${Clientes.ID},${Clientes.NOMBRES},${Clientes.APELLIDOS},${Clientes.DIRECCION},${Clientes.TELEFONO} FROM ${appGeneral.TABLENAME} where ${Clientes.ID} = $id;"
                val c = db.rawQuery(qry, null)
                c.moveToFirst()
                do {
                    return  Cliente(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(
                                    4
                            )
                    )
                }while (c.moveToNext())
            }else{
                Toast.makeText(appGeneral.CONTEXT, "No hay ningún registro", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return null
        }catch (ex: Exception){
            Toast.makeText(appGeneral.CONTEXT, "No se puedo encontrar", Toast.LENGTH_SHORT).show()
            return null
        }
    }
    fun AgregarCliente(cliente: Cliente){
        try {
            val db= appGeneral.DB.writableDatabase
            val qry = "INSERT INTO ${appGeneral.TABLENAME} (${Clientes.NOMBRES},${Clientes.APELLIDOS},${Clientes.DIRECCION},${Clientes.TELEFONO})" +
                    " values ('${cliente.nombres}','${cliente.apellidos}','${cliente.direccion}','${cliente.telefono}');"
            db.execSQL(qry)
            db.close()
            Toast.makeText(appGeneral.CONTEXT, "Insertado", Toast.LENGTH_SHORT).show()
        }catch (ex: Exception){
            Toast.makeText(appGeneral.CONTEXT, "No se pudo registrar", Toast.LENGTH_SHORT).show()
        }
    }
    fun ActualizarCliente(cliente: Cliente){
        try {
            val db= appGeneral.DB.writableDatabase
            val qry = "UPDATE ${appGeneral.TABLENAME} SET ${Clientes.NOMBRES}='${cliente.nombres}',${Clientes.APELLIDOS}='${cliente.apellidos}',${Clientes.DIRECCION}='${cliente.direccion}',${Clientes.TELEFONO}='${cliente.telefono}'" +
                    " WHERE ${Clientes.ID} = ${cliente.id};"
            db.execSQL(qry)
            db.close()
            Toast.makeText(appGeneral.CONTEXT, "Actualizado", Toast.LENGTH_SHORT).show()
        }catch (ex: Exception){
            Toast.makeText(appGeneral.CONTEXT, "No se pudo actualizar", Toast.LENGTH_SHORT).show()
        }
    }
    fun EliminarCliente(id:Int){
        try {
            val db= appGeneral.DB.writableDatabase
            val qry = "DELETE FROM ${appGeneral.TABLENAME} where ${Clientes.ID} = $id;"
            db.execSQL(qry)
            db.close()
        }
        catch (ex: Exception){
            Toast.makeText(appGeneral.CONTEXT, "No se pudo eliminar", Toast.LENGTH_SHORT).show()
        }
    }
}