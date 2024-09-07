package com.example.deviceinfo

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
    lateinit var binding: FragmentBatteryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBatteryBinding.inflate(inflater, container, false)

        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            context?.registerReceiver(null, ifilter)
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

        binding.batteryPercentage.text = "$batteryPct%"
        if (batteryPct != null) {
            if (batteryPct.toDouble() == 0.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_0_bar_24)
            } else if (batteryPct.toDouble() in 0.0..20.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_1_bar_24)
            } else if (batteryPct.toDouble() in 21.0..40.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_2_bar_24)
            } else if (batteryPct.toDouble() in 41.0..60.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_3_bar_24)
            } else if (batteryPct.toDouble() in 61.0..80.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_4_bar_24)
            } else if (batteryPct.toDouble() in 81.0..89.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_5_bar_24)
            } else if (batteryPct.toDouble() in 90.0..99.0) {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_6_bar_24)
            } else {
                binding.batteryIcon.setImageResource(R.drawable.baseline_battery_full_24)
            }
        } else {
            binding.batteryIcon.setImageResource(R.drawable.baseline_battery_std_24)
        }
        if (isCharging) {
            if (usbCharge) {
                binding.batteryStatus.text = "USB Charging!"
            } else if (acCharge) {
                binding.batteryStatus.text = "AC Charging!"
            } else {
                binding.batteryStatus.text = "Charging!"
            }

        } else {
            binding.batteryStatus.text = "On Battery!"
        }


        return binding.root
    }


}