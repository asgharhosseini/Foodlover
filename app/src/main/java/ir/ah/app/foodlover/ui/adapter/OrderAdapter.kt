package ir.ah.app.foodlover.ui.adapter


import android.view.*
import com.bumptech.glide.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.other.NumberHelper.EnglishToPersian
import kotlinx.android.synthetic.main.item_order.view.*
import javax.inject.*

class OrderAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Order>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(
                                R.layout.item_order,
                                parent, false
                        )
        )
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val order = differ.currentList[position]
        holder.itemView.apply {
            txt_itemOrder_title.text = order.title
            txt_itemOrder_price.text = EnglishToPersian(order.price) + " تومان"
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(order)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}