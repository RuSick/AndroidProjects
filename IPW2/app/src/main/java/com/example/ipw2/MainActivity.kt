package com.example.sqllitedemo

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import com.example.ipw2.DatabaseHandler
import com.example.ipw2.EmpModelClass
import com.example.ipw2.ItemAdapter
import com.example.ipw2.R
import kotlinx.android.synthetic.main.dialog_update.*

class MainActivity : AppCompatActivity() {

    var empModel = ArrayList<EmpModelClass>()
    private var adapter :ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_addRecord.setOnClickListener {view->
            addRecord(view)

        }


        setUpListOfDataIntoRecyclerView()

    }

    fun addRecord(view: View){

        var name = etName.text.toString()
        var type = etType.text.toString()
        var price = etPrice.text.toString().toInt()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (name.isEmpty() || type.isEmpty() || price==0){
            Toast.makeText(this, "Please Enter Both Name and Type with price", Toast.LENGTH_SHORT).show()
        }else{
            val status = databaseHandler.addEmployee(EmpModelClass(0, name,type,price))

            if (status> -1){
                Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show()
                etName.text.clear()
                etType.text.clear()
                etPrice.text.clear()
                setUpListOfDataIntoRecyclerView()


            }
        }
    }

    fun updateRecordDialog(empModelClass: EmpModelClass){
        val updateDialog = Dialog(this)
        updateDialog.setCancelable(false)
        updateDialog.setContentView(R.layout.dialog_update)

        updateDialog.etUpdateName.setText(empModelClass.tvName)
        updateDialog.etUpdateType.setText(empModelClass.tvType)
        updateDialog.etUpdatePrice.setText(empModelClass.tvPrice)
        updateDialog.btnUpdate.setOnClickListener ( View.OnClickListener { view->
            val name = updateDialog.etUpdateName.text.toString()
            val type = updateDialog.etUpdateType.text.toString()
            val price = updateDialog.etUpdatePrice.text.toString().toInt()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if (name.isEmpty() || type.isEmpty() || price==0){
                Toast.makeText(this, "Please Enter Name and type and price", Toast.LENGTH_SHORT).show()
            }else{

                val status = databaseHandler.updateEmployee(EmpModelClass(empModelClass.id, name, type, price))

                if (status > -1){
                    Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show()
                    setUpListOfDataIntoRecyclerView()
                    updateDialog.dismiss()// Dialog will be dismissed
                }

            }

        })

        updateDialog.btnCancel.setOnClickListener {
            updateDialog.dismiss()
        }

        //start the dialog
        updateDialog.show()

    }

    fun deleteRecordAlertDialog(empModelClass: EmpModelClass){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Record")
            .setMessage("Are You Sure You Want To Delete this entry?")
            .setPositiveButton("Yes"){ dialogInterface, which->
                val databaseHandler: DatabaseHandler = DatabaseHandler(this)
                val status =databaseHandler.deleteEmployee(EmpModelClass(empModelClass.id, "", "", 0))

                if (status> -1){
                    Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show()
                    setUpListOfDataIntoRecyclerView()
                }
                dialogInterface.dismiss()

            }
            .setNegativeButton("No"){dialogInterface, which ->
                dialogInterface.dismiss()
            }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    private fun setUpListOfDataIntoRecyclerView(){
        if (getItemList().size>0){

            adapter = ItemAdapter(this, getItemList())
            recyclerView.adapter =adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.visibility = View.VISIBLE


        }else{
            Toast.makeText(this, "No Records Present", Toast.LENGTH_SHORT).show()
            recyclerView.visibility = View.GONE
        }
    }

    private fun getItemList(): ArrayList<EmpModelClass>{
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        return databaseHandler.viewEmployee()
    }
}