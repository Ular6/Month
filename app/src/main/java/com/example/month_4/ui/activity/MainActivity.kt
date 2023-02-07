package com.example.month_4.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.month_4.R
import com.example.month_4.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val navView: BottomNavigationView = binding!!.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.newTaskFragment,
                R.id.navigation_profile,
                R.id.onBoardFragment,
                R.id.authFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        if ( com.example.month_4.ui.utils.Preferences(this).getHaveSeenOnBoarding()) {
            navController.navigate(
                R.id.navigation_home)
        }else{
            navController.navigate(
                R.id.onBoardFragment
            )
        }

        navController.addOnDestinationChangedListener { _, dest, _ ->
            navView.visibility =
                if (dest.id == R.id.newTaskFragment || dest.id == R.id.onBoardFragment || dest.id == R.id.authFragment) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.burger_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}


