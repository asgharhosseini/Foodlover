package ir.ah.app.foodlover.ui.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem


        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    var onItemClickListener: ((T) -> Unit)? = null

    @JvmName("setOnItemClickListener1")
    fun setOnItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }
}