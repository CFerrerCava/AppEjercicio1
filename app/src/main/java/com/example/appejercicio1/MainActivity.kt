package com.example.appejercicio1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val AdminCliente = AdminSQL()
    lateinit var nombres:ArrayList<Cliente>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Eliminar()
        update()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate  = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)
        return  true
    }
    override fun onStart() {
        super.onStart()
        ListarClientes()
    }
    fun ListarClientes(){
        val lista  = arrayListOf<String>()
        nombres  = AdminCliente.ObtenerTodos()!!
        for ( i in nombres.indices){
            val itemNombres = nombres.get(i)
            lista.add(itemNombres.id.toString()+" - "+itemNombres.nombres+" - "+itemNombres.apellidos+" - "+itemNombres.direccion)
        }
        val adaptador = ArrayAdapter(appGeneral.CONTEXT, android.R.layout.simple_expandable_list_item_1, lista)
        listClientes.adapter = adaptador
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item!!.itemId){
            R.id.action_agregar->{
                val intentAgregar= Intent(applicationContext,agregarCliente::class.java)
                startActivity(intentAgregar)
                return true
            }
            else->return super.onOptionsItemSelected(item)
        }

    }
    fun Eliminar(){
        listClientes.onItemLongClickListener= AdapterView.OnItemLongClickListener { parent, view, position, id ->
            val id=nombres.get(position).id
            Toast.makeText(appGeneral.CONTEXT,id.toString(), Toast.LENGTH_LONG).show()
            val dialog= AlertDialog.Builder(this)
            dialog.setTitle("Confirmación")
            dialog.setMessage("Confirme si desea eliminar?")
            dialog.setPositiveButton("SI"){dialogInterface,i->
                AdminCliente.EliminarCliente(id)
                ListarClientes()
            }
            dialog.setNegativeButton("NO"){dialogInterface,i->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }
    }

    fun update (){
        listClientes.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val id=nombres.get(position).id
            val intentAgregar= Intent(applicationContext,agregarCliente::class.java)
            intentAgregar.putExtra("id",id)
            startActivity(intentAgregar)
            true
        }
    }
}