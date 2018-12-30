package fontaine.nice.multiscreencontroller

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import fontaine.nice.multiscreencontroller.views.EffectFragment
import fontaine.nice.multiscreencontroller.views.SceneFragment
import fontaine.nice.multiscreencontroller.views.ScreenFragment
import kotlinx.android.synthetic.main.activity_select.*


class SelectActivity : AppCompatActivity() {

    private var toolbar: ActionBar? = null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.navigation_scenes -> {
                toolbar!!.setTitle(R.string.title_scenes)
                fragment = SceneFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_screens -> {
                toolbar!!.setTitle(R.string.title_screens)
                fragment = ScreenFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_effects -> {
                toolbar!!.setTitle(R.string.title_effects)
                fragment = EffectFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar = supportActionBar
        toolbar!!.setTitle(R.string.title_scenes)
        loadFragment(SceneFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
