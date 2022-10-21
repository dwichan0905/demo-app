package com.vikiwahyudi.deteksigempadantsunami.ui.dirasakan

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.databinding.ItemGempaBinding
import com.vikiwahyudi.deteksigempadantsunami.ui.detailgempa.DetailGempaActivity


class DirasakanAdapter : RecyclerView.Adapter<DirasakanAdapter.DirasakanViewHolder>() {
    private var listGempa = ArrayList<DirasakanEntity>()

    inner class DirasakanViewHolder(private val binding: ItemGempaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gempaDirasakan: DirasakanEntity) {
            with(binding) {
                tvTime.text = gempaDirasakan.tanggal + " " + gempaDirasakan.jam
                tvKedalaman.text = gempaDirasakan.kedalaman
                tvMagnitudo.text = gempaDirasakan.magnitude
                tvLocation.text = gempaDirasakan.wilayah
                tvPotensiDirasakan.text = gempaDirasakan.prediksi
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailGempaActivity::class.java)
                intent.putExtra(DetailGempaActivity.EXTRA_DETAIL_GEMPA, gempaDirasakan)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun setListGempa(gempaDirasakan: List<DirasakanEntity>) {
        this.listGempa.clear()
        this.listGempa.addAll(gempaDirasakan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DirasakanAdapter.DirasakanViewHolder {
        val itemGempaBinding =
            ItemGempaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DirasakanViewHolder(itemGempaBinding)
    }

    override fun onBindViewHolder(holder: DirasakanAdapter.DirasakanViewHolder, position: Int) {
        val gempaDirasakan = listGempa[position]
        holder.bind(gempaDirasakan)
    }

    override fun getItemCount(): Int = listGempa.size
}