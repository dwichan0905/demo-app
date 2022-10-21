package com.vikiwahyudi.deteksigempadantsunami.ui.edukasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vikiwahyudi.deteksigempadantsunami.databinding.NestedItemEdukasiBinding
import com.vikiwahyudi.deteksigempadantsunami.utils.EdukasiEntity

class NestedAdapter : RecyclerView.Adapter<NestedAdapter.NestedViewHolder>() {
    private var mList = ArrayList<EdukasiEntity>()

    class NestedViewHolder(private val binding: NestedItemEdukasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.nestedTvEdukasi.text = item
        }

    }

    fun setNestedAdapter(mList: List<EdukasiEntity>) {
        this.mList.clear()
        this.mList.addAll(mList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        val nestedItemEdukasiBinding =
            NestedItemEdukasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NestedViewHolder(nestedItemEdukasiBinding)
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        val item = mList[position]
        holder.bind(item.sub_title)
    }

    override fun getItemCount(): Int = mList.size
}