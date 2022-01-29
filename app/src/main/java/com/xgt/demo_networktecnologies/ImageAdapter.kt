package com.xgt.demo_networktecnologies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xgt.demo_networktecnologies.databinding.ItemImageBinding
import com.xgt.demo_networktecnologies.extension.imageUrl
import com.xgt.demo_networktecnologies.model.Image

class ImageAdapter(private val imageClickedListener: (Image) -> Unit) : ListAdapter<Image, ImageAdapter.ViewHolder>(ImageItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = getItem(position)
        holder.binding.ivTecno.imageUrl(image.imageUrl)
        holder.binding.root.setOnClickListener {
            imageClickedListener(image)
        }
    }
    inner class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}

class ImageItemCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem.id == newItem.id
}