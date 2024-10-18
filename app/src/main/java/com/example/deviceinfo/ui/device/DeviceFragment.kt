package com.example.deviceinfo.ui.device

import android.app.ActivityManager
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.deviceinfo.databinding.FragmentDeviceBinding

class DeviceFragment : Fragment() {

	private lateinit var binding: FragmentDeviceBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentDeviceBinding.inflate(inflater, container, false)
		val model = Build.MODEL
		val manufacturer=Build.MANUFACTURER
		val tags=Build.TAGS
		val board=Build.BOARD
		val display=activity?.resources?.displayMetrics
		val density=display?.xdpi
		val resolution="${display?.widthPixels} x ${display?.heightPixels}"

		val ramTotal=ActivityManager.MemoryInfo().availMem

		binding.apply {
			deviceModelTV.text="Model: $model"
			deviceManufacturerTV.text="Manufacturer: $manufacturer"
			deviceBoardTV.text="Board: $board"
			deviceScreenSizeTV.text="Screen Size: $display "
			deviceScreenDensityTV.text="Density: $density dpi"
			deviceResolutionTV.text="Screen Resolution: $resolution"
			deviceTotalRAM.text="Total RAM: $ramTotal"
		}

		return binding.root
	}



}