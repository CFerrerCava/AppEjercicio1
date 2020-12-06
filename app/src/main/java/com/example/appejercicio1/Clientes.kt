package com.example.appejercicio1

import android.provider.BaseColumns

class Clientes:BaseColumns {
    companion object{
        val ID="id"
        val NOMBRES="nombres"
        val APELLIDOS="apellidos"
        val DIRECCION="direccion"
        val TELEFONO="telefono"
    }
}