package com.vikiwahyudi.deteksigempadantsunami.ui.magnitude

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikiwahyudi.deteksigempadantsunami.databinding.FragmentMagnitudoBinding
import com.vikiwahyudi.deteksigempadantsunami.viewmodel.ViewModelFactory


class MagnitudoFragment : Fragment() {
    private var _fragmentMagnitudoBinding: FragmentMagnitudoBinding? = null
    private val fragmentMagnitudoBinding get() = _fragmentMagnitudoBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMagnitudoBinding =
            FragmentMagnitudoBinding.inflate(layoutInflater, container, false)
        return fragmentMagnitudoBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = requireActivity().actionBar
        actionBar?.setTitle("Terkini")

        if (activity != null) {
            showProgressBar(true)

            val magnitudoAdapter = MagnitudoAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MagnitudoViewModel::class.java]
            viewModel.getGempaMagnitudo().observe(viewLifecycleOwner, { gempaMagnitudo ->
                showProgressBar(false)
                magnitudoAdapter.setListGempa(gempaMagnitudo)
                magnitudoAdapter.notifyDataSetChanged()
            })

            with(fragmentMagnitudoBinding.rvGempa) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = magnitudoAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMagnitudoBinding = null
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMagnitudoBinding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}