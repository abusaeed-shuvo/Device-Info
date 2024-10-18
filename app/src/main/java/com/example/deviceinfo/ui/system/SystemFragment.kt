package com.example.deviceinfo.ui.system

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deviceinfo.databinding.FragmentSystemBinding


class SystemFragment : Fragment() {
	private lateinit var binding: FragmentSystemBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSystemBinding.inflate(inflater, container, false)

		val release = Build.VERSION.RELEASE
		val sdkVersion = Build.VERSION.SDK_INT
		val apiLevel = Build.VERSION_CODES.S
		val securityPatch = Build.VERSION.SECURITY_PATCH
		val bootloader = Build.BOOTLOADER
		val buildId = Build.ID
		val arch = Build.SUPPORTED_ABIS



		binding.apply {
			systemAndroidVersionTV.text = "Android Version: $sdkVersion (Release: $release)"
			systemApiLevelTV.text = "API Level: $apiLevel"
			systemSecurityPatchLevelTV.text = "Security Patch: $securityPatch"
			systemBootloaderTV.text = "Bootloader: $bootloader"
			systemKernelArchitectureTV.text = "Kernel Architecture: $arch"
			systemBuildIdTV.text = "Build ID: $buildId"
		}


		return binding.root
	}

}