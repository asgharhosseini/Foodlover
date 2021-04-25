package ir.ah.app.foodlover.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import kotlinx.android.synthetic.main.item_recommended.view.*
import javax.inject.Inject


class RecommendedAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Restaurant>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_recommended,
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
            glide.load(restaurant.image).into(img_itemRecommended)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}