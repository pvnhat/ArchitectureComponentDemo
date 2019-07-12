package com.vannhat.architecturecomponentdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by GianhTran on 08/07/2019.
 * tran.nguyen.song.gianh@framgia.com
 */
object Navigator {

    fun addFragment(
        fragmentManager: FragmentManager, fragment: Fragment, frameId: Int,
        addToBackStack: Boolean = false, tag: String? = null, bundle: Bundle? = null
    ) {
        val transaction = fragmentManager.beginTransaction()
        if (fragment.arguments == null) {
            fragment.arguments = bundle
        }

        transaction.add(frameId, fragment, tag)
        commitTransaction(transaction, addToBackStack)
    }

    fun findFragment(fragmentManager: FragmentManager, tag: String? = null,
        id: Int? = null): Fragment? {
        return when {
            tag != null -> fragmentManager.findFragmentByTag(tag)
            id != null -> fragmentManager.findFragmentById(id)
            else -> null
        }
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int,
        addToBackStack: Boolean = false, tag: String? = null) {
        val transaction = fragmentManager.beginTransaction().replace(frameId, fragment, tag)
        commitTransaction(transaction, addToBackStack)
    }

    private fun commitTransaction(transaction: FragmentTransaction, addToBackStack: Boolean) {
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createLog(message: String) {
        Log.d("cccc", message)
    }
}
