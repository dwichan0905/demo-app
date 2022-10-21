package com.vikiwahyudi.deteksigempadantsunami.ui.edukasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.databinding.ItemEdukasiBinding
import com.vikiwahyudi.deteksigempadantsunami.utils.EdukasiData

class EdukasiAdapter : RecyclerView.Adapter<EdukasiAdapter.EdukasiViewHolder>() {

    private var mListEdukasi = ArrayList<DataModelEdukasi>()

    fun setItemAdapter() {
        val model1 = DataModelEdukasi(EdukasiData.sebelumGempa())
        val model2 = DataModelEdukasi(EdukasiData.saatGempa())
        val model3 = DataModelEdukasi(EdukasiData.setelahGempa())
        mListEdukasi.clear()
        mListEdukasi.add(model1)
        mListEdukasi.add(model2)
        mListEdukasi.add(model3)
    }

    class EdukasiViewHolder(private var binding: ItemEdukasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modelEdukasi: DataModelEdukasi) {
            with(binding) {
                txtItemEdukasi.text = modelEdukasi.edukasi.title

                var isExpandable = modelEdukasi.isExpandable
                setExpandableItem(isExpandable, arrowImageview, expandableLayout)

                val nestedAdapter = NestedAdapter()
                nestedAdapter.setNestedAdapter(modelEdukasi.edukasi.edukasi_content)
                childRvEdukasi.layoutManager = LinearLayoutManager(itemView.context)
                childRvEdukasi.setHasFixedSize(true)
                childRvEdukasi.adapter = nestedAdapter


                linearLayout.setOnClickListener {
                    isExpandable = !isExpandable
                    setExpandableItem(isExpandable, arrowImageview, expandableLayout)
                }
            }
        }

        fun setExpandableItem(
            isExpandable: Boolean,
            arrowImageview: ImageView,
            expandableLayout: RelativeLayout
        ) {
            if (isExpandable) {
                arrowImageview.setImageResource(R.drawable.ic_arrow_up)
                expandableLayout.visibility = View.VISIBLE
            } else {
                arrowImageview.setImageResource(R.drawable.ic_arrow_down)
                expandableLayout.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EdukasiViewHolder {
        val itemEdukasiBinding =
            ItemEdukasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EdukasiViewHolder(itemEdukasiBinding)
    }

    override fun onBindViewHolder(holder: EdukasiViewHolder, position: Int) {
        val model = mListEdukasi[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = mListEdukasi.size


}