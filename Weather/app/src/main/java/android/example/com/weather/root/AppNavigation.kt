package android.example.com.weather.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface AppNavigation {

    fun moveTo(fragmentManager: FragmentManager, container: Int, fragment: Fragment){
        fragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack("true")
            .setReorderingAllowed(true)
            .commit()
    }
}