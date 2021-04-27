package ir.ah.app.foodlover.ui.adapter


import android.view.*
import com.bumptech.glide.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.maincourse.*
import ir.ah.app.foodlover.other.NumberHelper.EnglishToPersian
import kotlinx.android.synthetic.main.item_food.view.*
import javax.inject.*

class MainCourseAdapter @Inject constructor(
        private val glide: RequestManager
) : BaseAdapter<MainCourse>() {


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
        val mainCourse = differ.currentList[position]
        holder.itemView.apply {
            txt_itemFood_title.text = mainCourse.title
            txt_itemFood_Price.text = EnglishToPersian(mainCourse.price) + " :تومان"
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(mainCourse)
                }
            }
            glide.load(mainCourse.image).into(img_itemFood)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}