package com.example.deviceinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.deviceinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var navController: NavController


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.mainToolbar.root)

		val drawerLayout = binding.main
		val navView = binding.navigationView

		navController = findNavController(R.id.fragmentContainerView)

		appBarConfiguration = AppBarConfiguration(

			setOf(
				R.id.socFragment,
				R.id.batteryFragment,
				R.id.aboutFragment,
				R.id.deviceFragment,
				R.id.systemFragment,
				R.id.thermalFragment,
				R.id.sensorsFragment
			), drawerLayout
		)

		setupActionBarWithNavController(navController, drawerLayout)

		navView.setupWithNavController(navController)

	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}


}