package com.vannhat.architecturecomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by GianhTran on 08/07/2019.
 * tran.nguyen.song.gianh@framgia.com
 */
object Navigator {

    fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int,
        addToBackStack: Boolean = false, tag: String? = null, bundle: Bundle? = null) {
        val transaction = fragmentManager.beginTransaction()
        if (fragment.arguments == null) {
            fragment.arguments = bundle
        }

        transaction.add(frameId, fragment, tag)
        transaction.commit()
    }
}
