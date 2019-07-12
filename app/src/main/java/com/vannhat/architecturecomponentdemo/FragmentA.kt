package com.vannhat.architecturecomponentdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vannhat.architecturecomponentdemo.Navigator.createLog
import com.vannhat.architecturecomponentdemo.test.FragmentB
import kotlinx.android.synthetic.main.fragment_a.*

/**
 * Created by GianhTran on 08/07/2019.
 * tran.nguyen.song.gianh@framgia.com
 */
class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createLog("onCreateView")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    private fun handleEvents() {
        btn_go_fragb.setOnClickListener {
            (requireActivity() as MainActivity).createFragmentB(targetData = "Nhat from FragB",
                fragment = this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return

        if (requestCode == MainActivity.TARGET_DATE_REQUEST_CODE && data != null)
            tv_text_fromB.text = data.getStringExtra(FragmentB.TEXT_FROM_FRAG_B)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createLog("ActivityCreated")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createLog("ViewCreated")
        handleEvents()
    }

    override fun onResume() {
        super.onResume()
        createLog("resume")
    }

    override fun onPause() {
        super.onPause()
        createLog("pause")
    }

    override fun onStop() {
        super.onStop()
        createLog("stop")
    }

    override fun onStart() {
        super.onStart()
        createLog("start")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        createLog("viewStateRestored")
    }

    companion object {
        const val TAG = "FragmentA"
        fun newInstance() = FragmentA()
    }

}