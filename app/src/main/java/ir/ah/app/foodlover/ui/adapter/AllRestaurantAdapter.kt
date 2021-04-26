package ir.ah.app.foodlover.ui.adapter


import android.view.*
import com.bumptech.glide.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.restaurant.*
import kotlinx.android.synthetic.main.item_allrestaurant.view.*
import javax.inject.*

class AllRestaurantAdapter @Inject constructor(
        private val glide: RequestManager
) : BaseAdapter<Restaurant>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(
                                R.layout.item_allrestaurant,
                                parent, false
                        )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val restaurant = differ.currentList[position]
        holder.itemView.apply {
            txt_itemPopular_title.text = restaurant.title
            txt_itemPopular_category.text = restaurant.categories
            chip_popular_distance.text = restaurant.distance
            chip_popular_distanceTime.text = restaurant.timeDistance
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(restaurant)
                }
            }
            glide.load(restaurant.image).into(img_itemPopular)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}