package com.example.jedzonko

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
        private var navController: NavController? = null
        private var appBarConfiguration: AppBarConfiguration? = null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.activity_main)
            this.supportActionBar?.hide()
            navController =
                (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController

            appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.calculatorFragment, R.id.scanFragment))
            setupActionBar()
            setupBottomNavigationMenu()
            var bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
            bottomNavigation.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menuHome -> {
                        navController!!.navigate(R.id.mainFragment)
                        true
                    }
                    R.id.menuCalculator -> {
                        navController!!.navigate(R.id.calculatorFragment)
                        true
                    }
                    R.id.menuScanner -> {
                        navController!!.navigate(R.id.scanFragment)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

    private fun setupActionBar() {
        NavigationUI.setupActionBarWithNavController(
            this,
            navController!!,
            appBarConfiguration!!
        )
    }

    private fun setupBottomNavigationMenu() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).let {
            NavigationUI.setupWithNavController(it, navController!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navigated = NavigationUI.onNavDestinationSelected(item, navController!!)

        return navigated || super.onOptionsItemSelected(item)
    }
}