package com.vannhat.architecturecomponentdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var number = MutableLiveData<Int>()

    private var covertNum: LiveData<String>

    init {
        covertNum = Transformations.switchMap(number) { number ->
            coverNumber(number)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        handleEvents()
        observer()
    }

    private fun initData() {
        number.value = 1
    }

    private fun handleEvents() {
        btn_increase.setOnClickListener {
            number.value = number.value?.plus(1)
        }
    }

    private fun observer() {
        covertNum.observe(this, Observer<String> {
            tv_number.text = it.toString()
        })
    }

    // using with switchMap
    private fun coverNumber(number: Int): LiveData<String> {
        val liveData = MutableLiveData<String>()
        liveData.value = "Number $number"
        return liveData
    }

    // using with map
    private fun coverNumberMap(number: Int): String {
        return "Number $number"
    }
}
