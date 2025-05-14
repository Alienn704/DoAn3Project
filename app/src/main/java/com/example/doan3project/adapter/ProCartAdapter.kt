package com.example.doan3project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doan3project.Model.ProductCartModel
import com.example.doan3project.R
import com.google.android.material.textview.MaterialTextView

class ProCartAdapter(private val ds: ArrayList<ProductCartModel>): RecyclerView.Adapter<ProCartAdapter.ViewHolder>() {
    private lateinit var bListener: OnClickListener

    interface OnClickListener{
        fun onClickBlog(position: Int)
    }
//du
    fun setOnItemClickListener(clickListener: OnClickListener){
        bListener = clickListener
    }
    class ViewHolder(itemView: View, clickListener: OnClickListener): RecyclerView.ViewHolder(itemView){
        var imageView: ImageView? = null
        init {
            imageView = itemView.findViewById(R.id.product_image);
            itemView.setOnClickListener {
                clickListener.onClickBlog(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_cart, parent, false)
        return ViewHolder(view, bListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.title_product).text = ds[position].PrdName
            findViewById<TextView>(R.id.price_product).text = ds[position].PrdPrice.toString()
            findViewById<TextView>(R.id.amount_product).text = ds[position].amount.toString()
            val imgView = findViewById<ImageView>(R.id.image_cart)
            Glide.with(context)
                .load("http://192.168.1.5/doan3/uploads/product/${ds[position].PrdImages}")
                .thumbnail(0.1f)
                .into(imgView)
        }
    }


}