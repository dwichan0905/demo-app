package com.vikiwahyudi.deteksigempadantsunami.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var _fragmentSettingBinding: FragmentSettingsBinding? = null
    private val fragmentSettingsBinding get() = _fragmentSettingBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentSettingBinding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        childFragmentManager.beginTransaction().add(R.id.settings, MyPreferenceFragment()).commit()
        return fragmentSettingsBinding.root

    }



}