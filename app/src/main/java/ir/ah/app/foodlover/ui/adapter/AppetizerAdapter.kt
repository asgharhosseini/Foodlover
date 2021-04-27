package ir.ah.app.foodlover.ui.adapter


import android.view.*
import com.bumptech.glide.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.appetizer.*
import ir.ah.app.foodlover.other.NumberHelper.EnglishToPersian
import kotlinx.android.synthetic.main.item_food.view.*
import javax.inject.*

class AppetizerAdapter @Inject constructor(
        private val glide: RequestManager
) : BaseAdapter<Appetizer>() {


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
        val appetizer = differ.currentList[position]
        holder.itemView.apply {
            txt_itemFood_title.text = appetizer.title
            txt_itemFood_Price.text = EnglishToPersian(appetizer.price) + " :تومان"
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(appetizer)
                }
            }
            glide.load(appetizer.image).into(img_itemFood)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}