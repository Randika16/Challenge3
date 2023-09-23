package com.example.challenge2_foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.data.Makanan
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class MakananAdapter(private val makanan: List<Makanan>, private val context: Context,) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Makanan)
    }

    var isListView = true
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        private const val TYPE_LIST = 1
        private const val TYPE_GRID = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LIST -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_makanan, parent, false)
                ListViewHolder(view)
            }
            TYPE_GRID -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_makanan_grid, parent, false)
                GridViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val makanan = makanan[position]

        when (holder.itemViewType) {
            TYPE_LIST -> {
                val listViewHolder = holder as ListViewHolder
                listViewHolder.makananTitle.text = makanan.nama
                listViewHolder.makananHarga.text = makanan.harga
                listViewHolder.imageMakanan.setImageResource(makanan.gambar)
                holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(makanan) }
            }
            TYPE_GRID -> {
                val gridViewHolder = holder as GridViewHolder
                gridViewHolder.makananTitle.text = makanan.nama
                gridViewHolder.makananHarga.text = makanan.harga
                gridViewHolder.imageMakanan.setImageResource(makanan.gambar)
                holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(makanan) }
            }
        }
    }

    override fun getItemCount(): Int {
        return makanan.size
    }

    override fun getItemViewType(position: Int): Int {
        // Return tipe item berdasarkan posisi (misalnya, pilih TYPE_LIST atau TYPE_GRID)
        return if (isListView) {
            TYPE_LIST
        } else {
            TYPE_GRID
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val makananTitle: MaterialTextView = itemView.findViewById(R.id.tv_makanan)
        val makananHarga: MaterialTextView = itemView.findViewById(R.id.tv_harga)
        val imageMakanan: ShapeableImageView = itemView.findViewById(R.id.iv_makanan)
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val makananTitle: MaterialTextView = itemView.findViewById(R.id.tv_nama_grid)
        val makananHarga: MaterialTextView = itemView.findViewById(R.id.tv_harga_grid)
        val imageMakanan: ShapeableImageView = itemView.findViewById(R.id.iv_makanan_grid)
    }
}