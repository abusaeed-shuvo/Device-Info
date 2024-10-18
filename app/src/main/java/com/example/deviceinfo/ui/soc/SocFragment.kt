package com.example.deviceinfo.ui.soc

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.deviceinfo.databinding.FragmentSocBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.regex.Pattern
import kotlin.math.max


class SocFragment : Fragment() {
	private lateinit var binding: FragmentSocBinding

	@RequiresApi(Build.VERSION_CODES.S)
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSocBinding.inflate(inflater, container, false)

		val socModel = Build.SOC_MODEL
		val model = Build.MODEL
		val socName = getCpuName()
		val cores = getCPUCoreNum()

		binding.apply {
			socNameTV.text = "SOC Name: $socName"
			socModelTV.text = "SOC Model: $socModel"
			socCoreNumbers.text = "Cores: $cores"
		}


		return binding.root
	}

	private fun getCPUCoreNum(): Int {
		val pattern = Pattern.compile("cpu[0-9]+")
		return max(
			File("/sys/devices/system/cpu/")
				.walk()
				.maxDepth(1)
				.count { pattern.matcher(it.name).matches() },
			Runtime.getRuntime().availableProcessors()
		)
	}

	private fun getCpuName(): String? {
		try {
			val fr = FileReader("/proc/cpuinfo")
			val br = BufferedReader(fr)
			val text = br.readLine()
			br.close()
			val array = text.split(":\\s+".toRegex(), limit = 2).toTypedArray()
			if (array.size >= 2) {
				return array[1]
			}
		} catch (e: FileNotFoundException) {
			e.printStackTrace()
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return null
	}
}