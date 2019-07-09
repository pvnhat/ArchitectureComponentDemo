package com.vannhat.architecturecomponentdemo.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vannhat.architecturecomponentdemo.R
import kotlinx.android.synthetic.main.activity_test_intent.*

class TestIntent : AppCompatActivity() {

    private var adapter: CheckboxAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent)

        initData()
    }

    private fun initData() {
        val implicitIntentData = intent.data
        Toast.makeText(this, "link : $implicitIntentData", Toast.LENGTH_SHORT).show()


        val listData = mutableListOf(
            Fruit("All"), Fruit("Banana"), Fruit("Orange", true),
            Fruit("Mango")
        )
        adapter = CheckboxAdapter { isFresh ->
            editData(listData, isFresh)
        }
        adapter?.dataList = listData
        rv_fruit.adapter = adapter
    }

    private fun editData(listData: List<Fruit>, isFresh:Boolean) {
        for (i in 0 until listData.size) {
            listData[i].isFresh = !isFresh
        }

        adapter?.let {
            it.updateData(listData)
            adapter?.notifyDataSetChanged()


        }
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, TestIntent::class.java)
        }
    }
}
