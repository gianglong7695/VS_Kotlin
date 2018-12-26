package com.vtv.sports.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.vtv.sports.R

/**
 * Created by Giang Long on 12/26/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class FragmentUtil {
    companion object {
        val SLIDE_RIGHT = 1
        val SLIDE_BOTTOM = 2

        fun addFragment(a: FragmentActivity, frameId: Int, frm: Fragment, isAddStack: Boolean, styleAnimation: Int) {
            if (a.supportFragmentManager != null) {
                val fragmentManager = a.supportFragmentManager
                val transaction = fragmentManager.beginTransaction()

                when (styleAnimation) {
                    SLIDE_RIGHT -> transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.stack_pop, R.anim.stack_push, R.anim.slide_out_to_right)
                    SLIDE_BOTTOM -> transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.stack_push, R.anim.stack_pop, R.anim.slide_out_bottom)
                    else -> transaction.setCustomAnimations(0, R.anim.stack_pop, 0, R.anim.slide_out_to_right)
                }

                if (isAddStack) {
                    transaction.add(frameId, frm).addToBackStack("1").commitAllowingStateLoss()
                } else {
                    transaction.add(frameId, frm).commitAllowingStateLoss()
                }
            }
        }


        fun popTopFragment(a: FragmentActivity) {
            if (a.supportFragmentManager != null) {
                if (a.supportFragmentManager.backStackEntryCount > 0) {
                    a.supportFragmentManager.popBackStack()
                }
            }
        }

        fun getBackStackCount (a: FragmentActivity):Int{
            if (a.supportFragmentManager != null) {
                return a.supportFragmentManager.backStackEntryCount
            }
            return 0
        }
    }

}