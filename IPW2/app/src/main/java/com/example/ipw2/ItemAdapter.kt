package com.example.ipw2

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ipw2.R
import com.example.sqllitedemo.MainActivity
import kotlinx.android.synthetic.main.items_row.view.*

class ItemAdapter(val context: Context, val items : ArrayList<EmpModelClass> )
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.tvName.text = item.tvName
        holder.tvType.text = item.tvType
        holder.tvPrice.text = item.tvPrice.toString()


        if(position% 2==0){
            holder.llMain.setCardBackgroundColor(ContextCompat.getColor(context, R.color.grey))
        }
        else{
            holder.llMain.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))

        }

        holder.ivEdit.setOnClickListener { view->

            if (context is MainActivity){
                context.updateRecordDialog(item)
            }
        }

        holder.ivDelete.setOnClickListener { view->
            if (context is MainActivity){
                context.deleteRecordAlertDialog(item)
            }
        }
    }

    override fun getItemCount(): Int {

        return items.size
    }




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvName = itemView.tvName
        val tvType = itemView.tvType
        val tvPrice = itemView.tvPrice
        val ivEdit = itemView.ivEdit
        val ivDelete = itemView.ivDelete
        val llMain = itemView.llMain

    }
}