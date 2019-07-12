package com.vannhat.architecturecomponentdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.vannhat.architecturecomponentdemo.Navigator.addFragment
import com.vannhat.architecturecomponentdemo.Navigator.findFragment
import com.vannhat.architecturecomponentdemo.Navigator.replaceFragment
import com.vannhat.architecturecomponentdemo.test.FragmentB
import com.vannhat.architecturecomponentdemo.test.TestIntent
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

        btn_go.setOnClickListener {
            startActivity(TestIntent.newInstance(this))
        }

        btn_create_frag_a.setOnClickListener {
            createFragmentA()
        }

        btn_create_frag_b.setOnClickListener {
            createFragmentB()
        }
    }

    private fun createFragmentA() {
        val frag = DialogFragment()
        var fragmentA = findFragment(supportFragmentManager, FragmentA.TAG)
        if (fragmentA == null) {
            fragmentA = FragmentA.newInstance()
            addFragment(supportFragmentManager, fragmentA, R.id.frame_frag, tag = FragmentA.TAG,
                addToBackStack = true)
        } else {
            replaceFragment(supportFragmentManager, fragmentA, R.id.frame_frag, tag = FragmentA.TAG,
                addToBackStack = true)
        }
    }

    fun createFragmentB(targetData: String? = null, fragment: Fragment? = null) {
        var fragmentB = findFragment(supportFragmentManager, FragmentB.TAG)
        if (fragmentB == null) {
            fragmentB = FragmentB.newInstance()

            if (targetData != null && fragment != null) fragmentB.setTargetFragment(fragment,
                TARGET_DATE_REQUEST_CODE)

            addFragment(supportFragmentManager, fragmentB, R.id.frame_frag, tag = FragmentB.TAG,
                addToBackStack = true)
        } else {
            replaceFragment(supportFragmentManager, fragmentB, R.id.frame_frag, tag = FragmentB.TAG,
                addToBackStack = true)
        }
    }

    private fun observer() {
        // covertNum.observe(this)
//        covertNum.observe(this, Observer<String> {
//            tv_number.text = it.toString()
//        })
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

    companion object {
        const val TARGET_DATE_REQUEST_CODE = 1
    }
}
