package com.example.deviceinfo

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deviceinfo.databinding.FragmentOsBinding


class OsFragment : Fragment() {
    lateinit var binding: FragmentOsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOsBinding.inflate(inflater, container, false)

        val baseOs = Build.VERSION.BASE_OS
        val codeName = Build.VERSION.CODENAME
        val incremental = Build.VERSION.INCREMENTAL
        val release = Build.VERSION.RELEASE
        val sdk = Build.VERSION.SDK_INT
        val securityPatch = Build.VERSION.SECURITY_PATCH

        binding.baseOs.text = "OS: $baseOs"
        binding.codeName.text = "Code Name: $codeName"
        binding.incremental.text = "Incremental: $incremental"
        binding.release.text = "Release: $release"
        binding.sdk.text = "SDK: $sdk"
        binding.securityPatch.text = "Security Patch: $securityPatch"



        return binding.root
    }


}