package tech.destinum.recorderis.activities

import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import tech.destinum.recorderis.Fragments.AboutFragment
import tech.destinum.recorderis.Fragments.HomeFragment
import tech.destinum.recorderis.Fragments.ProfileFragment

import tech.destinum.recorderis.R

class Home : AppCompatActivity() {

    private val mBotNav by lazy { findViewById(R.id.navigation) as BottomNavigationView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout, HomeFragment())
                .commit()


        mBotNav.setOnNavigationItemSelectedListener{ item ->
            var fragment: Fragment? = null
            when (item.itemId){
                R.id.action_home -> {
                    fragment = HomeFragment()
                    Toast.makeText(this, "Action Home", Toast.LENGTH_SHORT).show()
                }
                R.id.action_profile -> {
                    fragment = ProfileFragment()
                    Toast.makeText(this, "Action Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.action_info -> {
                    fragment = AboutFragment()
                    Toast.makeText(this, "Action Info", Toast.LENGTH_SHORT).show()
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit()
            true
        }




    }
}