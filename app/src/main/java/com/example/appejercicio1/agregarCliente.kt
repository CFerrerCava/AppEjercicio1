package com.example.appejercicio1

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_agregar_cliente.*

class agregarCliente : AppCompatActivity() {
    val AdminCliente = AdminSQL()
    var id = 0
    lateinit var _cliente:Cliente
    override fun onCreate(savedInstanceState: Bundle?) {
        val parametros = this.intent.extras
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cliente)
        if ( parametros != null){
            id = parametros.getInt("id")
            getClienteToActivity()
        }
        Guardar()
    }

    fun Guardar(){
        btnguardar.setOnClickListener{
            val cliente = Cliente(id, txtnombre.text.toString(), txtapellido.text.toString(), txtdireccion.text.toString(), txt_telefono.text.toString())
            if (id>0){
                AdminCliente.ActualizarCliente(cliente)
            }else{
                AdminCliente.AgregarCliente(cliente)
            }
            finish()
        }
    }
    fun getClienteToActivity(){
        _cliente = AdminCliente.ObtenerCliente(id)!!
        txtnombre.setText(_cliente.nombres)
        txtapellido.setText(_cliente.apellidos)
        txtdireccion.setText(_cliente.direccion)
        txt_telefono.setText(_cliente.telefono)
    }
}