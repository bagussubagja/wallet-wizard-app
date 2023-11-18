package com.mantequilla.walletwizardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
private lateinit var sharedPref: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMainActivity) as NavHostFragment
        navController = navHostFragment.navController
        sharedPref = PreferenceHelper(this)
        val isAuthenticated : Boolean = sharedPref.getBoolean(AuthObject.PREF_IS_LOGIN)
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