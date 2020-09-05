package com.tech.assignment.product.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.presentation.extensions.loadImage
import com.tech.assignment.product.R
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val onItemClickListener: ((String?) -> Unit)? = null) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var oldProductList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(oldProductList[position])
    }

    fun updateList(list: MutableList<ProductItem>) {
        oldProductList.clear()
        if (!list.isNullOrEmpty()) {
            oldProductList.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productItemData: ProductItem?) {
            with(itemView) {
                productItemData?.apply {
                    textViewProductName.text = name
                    textViewPrice.text = price.toString()
                    image?.loadImage(context = context, imageView = imageViewProduct)
                    itemView.setOnClickListener {
                        onItemClickListener?.invoke(productId)
                    }
                }

            }

        }
    }
}