package com.mantequilla.walletwizardapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.ActivityMenuBinding
import com.mantequilla.walletwizardapp.fragments.HomeFragment
import com.mantequilla.walletwizardapp.fragments.SettingFragment

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragmentId -> replaceFragment(HomeFragment())
                R.id.settingFragmentId -> replaceFragment(SettingFragment())
                else -> {}
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flMenuActivity, fragment)
        fragmentTransaction.commit()
    }
}