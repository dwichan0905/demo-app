package com.vikiwahyudi.deteksigempadantsunami.ui.edukasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikiwahyudi.deteksigempadantsunami.databinding.FragmentEdukasiBinding


class EdukasiFragment : Fragment() {

    private var _fragmentEdukasiBinding: FragmentEdukasiBinding? = null
    val fragmentEdukasiBinding get() = _fragmentEdukasiBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentEdukasiBinding = FragmentEdukasiBinding.inflate(layoutInflater, container, false)
        return fragmentEdukasiBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edukasiAdapter = EdukasiAdapter()
        edukasiAdapter.setItemAdapter()

        with(fragmentEdukasiBinding.rvEdukasi) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = edukasiAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentEdukasiBinding = null
    }
}