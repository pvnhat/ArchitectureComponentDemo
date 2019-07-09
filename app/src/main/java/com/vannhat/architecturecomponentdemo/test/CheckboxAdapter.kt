package com.vannhat.architecturecomponentdemo.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vannhat.architecturecomponentdemo.R
import kotlinx.android.synthetic.main.item_fruit.view.*

class CheckboxAdapter(var areAllChecked: (isFresh: Boolean) -> Unit) :
    RecyclerView.Adapter<CheckboxAdapter.CheckBoxHolder>() {

    var dataList = mutableListOf<Fruit>()

    fun updateData(newList: List<Fruit>) {
        dataList = newList as MutableList<Fruit>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBoxHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        return CheckBoxHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CheckBoxHolder, position: Int) {
        holder.bind(dataList[position], areAllChecked, position)
    }


    class CheckBoxHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName = itemView.tv_name
        private val cbIsFresh = itemView.cb_is_fresh

        fun bind(item: Fruit, isAllCheckedListener: (isFresh: Boolean) -> Unit, position: Int) {
            tvName.text = item.name
            cbIsFresh.isChecked = item.isFresh
            cbIsFresh.setOnCheckedChangeListener { _, _ ->
                if (position == 0)
                    isAllCheckedListener.invoke(item.isFresh)
            }
        }
    }
}