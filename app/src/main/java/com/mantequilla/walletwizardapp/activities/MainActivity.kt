package com.mantequilla.walletwizardapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mantequilla.walletwizardapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMainActivity) as NavHostFragment
        navController = navHostFragment.navController
        val isAuthenticated : Boolean = false
        if (isAuthenticated) {
            navController.setGraph(R.navigation.app_nav)
        } else {
            navController.setGraph(R.navigation.auth_nav)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}