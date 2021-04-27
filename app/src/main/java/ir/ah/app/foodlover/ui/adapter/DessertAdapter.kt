package ir.ah.app.foodlover.ui.adapter


import android.view.*
import com.bumptech.glide.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.dessert.*
import ir.ah.app.foodlover.other.NumberHelper.EnglishToPersian
import kotlinx.android.synthetic.main.item_food.view.*
import javax.inject.*

class DessertAdapter @Inject constructor(
        private val glide: RequestManager
) : BaseAdapter<Dessert>() {


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
        val dessert = differ.currentList[position]
        holder.itemView.apply {
            txt_itemFood_title.text = dessert.title
            txt_itemFood_Price.text = EnglishToPersian(dessert.price) + " :تومان"
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(dessert)
                }
            }
            glide.load(dessert.image).into(img_itemFood)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}