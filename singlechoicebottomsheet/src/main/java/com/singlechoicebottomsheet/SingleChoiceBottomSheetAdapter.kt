package com.singlechoicebottomsheet

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class SingleChoiceBottomSheetAdapter<T>(
    private val itemList: List<T>,
    private val fieldName: String,
    private val preSelectedItem: T?,
    private val onItemSelected: (T) -> Unit
) : RecyclerView.Adapter<SingleChoiceBottomSheetAdapter<T>.ViewHolder>() {

    var filteredList: List<T> = itemList.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_single_choice_bottom_sheet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvName.text = getDisplayText(position)
        holder.itemView.setOnClickListener {
            onItemSelected.invoke(filteredList[position])
        }

        if (preSelectedItem!=null && preSelectedItem == filteredList[position]){
            holder.itemView.setBackgroundColor(Color.parseColor("#DFD6D3D3"))
        }else{
            Log.v(""," onBindViewHolder : "+ preSelectedItem)
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    private fun getDisplayText(position: Int) : String {
        val obj = filteredList[position]!!
        return if (!fieldName.isNullOrBlank()) {

            val displayValue = obj::class.java.getDeclaredField(fieldName)
            displayValue.isAccessible = true
            displayValue.get(obj)?.toString() ?: ""

        }else{
            obj.toString()
        }

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }
    fun updateFilterList(list : List<T>){
        filteredList = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<AppCompatTextView>(R.id.tvName)!!
        // Bind your views here
    }
}