package tech.destinum.recorderis.Fragments

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import tech.destinum.recorderis.R

class FragmentAdapter(fm: FragmentManager, private val mContext: Context): FragmentStatePagerAdapter(fm){
    private val number_tabs = 3

    override fun getPageTitle(position: Int): CharSequence? {

        when (position) {
            0 -> return mContext.getString(R.string.nav_home)
            1 -> return mContext.getString(R.string.nav_profile)
            2 -> return mContext.getString(R.string.nav_info)
        }
        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeFragment()
//            1 -> return TableFragment()
//            2 -> return StatsFragment()
        }
        return null
    }

    override fun getCount(): Int {
        return number_tabs
    }

}