package com.technipixl.eval4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

	private lateinit var navHostFragment: NavHostFragment

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		navHostFragment = supportFragmentManager
			.findFragmentById(R.id.fragContainer) as NavHostFragment
		NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)
	}

	override fun onSupportNavigateUp() = navHostFragment.navController.navigateUp()
}