package ir.ah.app.foodlover.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.order.Order
import ir.ah.app.foodlover.other.NumberHelper.EnglishToPersian
import kotlinx.android.synthetic.main.item_food.view.*
import javax.inject.Inject

class OrderAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Order>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_food,
                    parent, false
                )
        )
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val order = differ.currentList[position]
        holder.itemView.apply {
            txt_itemFood_title.text = order.title
            txt_itemFood_Price.text = EnglishToPersian(order.price) + " :تومان"
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(order)
                }
            }
            glide.load(order.image).into(img_itemFood)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}