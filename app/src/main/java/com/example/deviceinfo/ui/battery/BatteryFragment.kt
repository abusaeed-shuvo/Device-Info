package com.example.deviceinfo.ui.battery

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deviceinfo.databinding.FragmentBatteryBinding


class BatteryFragment : Fragment() {
	private lateinit var binding: FragmentBatteryBinding


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentBatteryBinding.inflate(inflater, container, false)

		val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { iFilter ->
			context?.registerReceiver(null, iFilter)
		}
		val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
		val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
		                          || status == BatteryManager.BATTERY_STATUS_FULL

		// How are we charging?
		val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
		val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
		val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

		val batteryPct: Float? = batteryStatus?.let { intent ->
			val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
			val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
			level * 100 / scale.toFloat()
		}








		return binding.root
	}


}