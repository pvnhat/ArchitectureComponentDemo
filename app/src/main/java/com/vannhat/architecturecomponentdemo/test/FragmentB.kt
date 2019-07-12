package com.vannhat.architecturecomponentdemo.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vannhat.architecturecomponentdemo.MainActivity
import com.vannhat.architecturecomponentdemo.Navigator
import com.vannhat.architecturecomponentdemo.R
import kotlinx.android.synthetic.main.fragment_b.*

/**
 * Created by VanNhat on 09/07/2019.
 */
class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Navigator.createLog("onCreateView B")
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    private fun handleEvents() {
        btn_pass_content.setOnClickListener {
            val text = edt_content.text.toString()
            val intent = Intent()
            intent.putExtra(TEXT_FROM_FRAG_B, text)
            targetFragment?.onActivityResult(MainActivity.TARGET_DATE_REQUEST_CODE,
                Activity.RESULT_OK, intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Navigator.createLog("ActivityCreated B")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Navigator.createLog("ViewCreated B")
        handleEvents()
    }

    override fun onResume() {
        super.onResume()
        Navigator.createLog("resume B")
    }

    override fun onPause() {
        super.onPause()
        Navigator.createLog("pause B")
    }

    override fun onStop() {
        super.onStop()
        Navigator.createLog("stop B")
    }

    override fun onStart() {
        super.onStart()
        Navigator.createLog("start B")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Navigator.createLog("viewStateRestored B")
    }

    companion object {
        const val TAG = "FragmentB"
        fun newInstance() = FragmentB()

        const val TEXT_FROM_FRAG_B = "TEXT_FROM_FRAG_B"
    }
}