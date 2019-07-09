package com.vannhat.architecturecomponentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by GianhTran on 08/07/2019.
 * tran.nguyen.song.gianh@framgia.com
 */
class FragmentA : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    companion object {
        const val TAG = "FragmentA"
        fun newInstance() = FragmentA()
    }

}