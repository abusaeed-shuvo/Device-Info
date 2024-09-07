package com.example.deviceinfo

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deviceinfo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val device = Build.DEVICE
        val user = Build.USER
        val abi = Build.SUPPORTED_ABIS.joinToString(",")

        binding.deviceManufacturer.text = "Manufacturer: $manufacturer"
        binding.deviceModel.text = "Model: $model"
        binding.deviceName.text = "Device Name: $device"
        binding.userName.text = "User Name: $user"
        binding.abiList.text = "Abi: $abi"

        return binding.root
    }


}