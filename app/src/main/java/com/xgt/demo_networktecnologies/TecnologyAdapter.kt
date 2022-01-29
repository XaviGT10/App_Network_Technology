package com.xgt.demo_networktecnologies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xgt.demo_networktecnologies.databinding.ItemTecnologyBinding
import com.xgt.demo_networktecnologies.extension.imageUrl
import com.xgt.demo_networktecnologies.model.Tecnology

class TecnologyAdapter (private val onTecClicked: (Tecnology) -> Unit) :
    ListAdapter<Tecnology, TecnologyAdapter.ViewHolder>(TecnologyCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTecnologyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tecnology = getItem(position)
        holder.binding.tvName.text = tecnology.name
        holder.binding.tvDescription.text = tecnology.description
        holder.binding.ivTech.imageUrl(tecnology.imageUrl)
        holder.binding.root.setOnClickListener {
            onTecClicked(tecnology)
        }
    }

    inner class ViewHolder(val binding: ItemTecnologyBinding) : RecyclerView.ViewHolder(binding.root)
}

class TecnologyCallback : DiffUtil.ItemCallback<Tecnology>(){
    override fun areItemsTheSame(oldItem: Tecnology, newItem: Tecnology): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Tecnology, newItem: Tecnology): Boolean = oldItem.id == newItem.id
}
