package ir.ah.app.foodlover.ui.adapter


import android.graphics.*
import android.view.*
import ir.ah.app.foodlover.*
import ir.ah.app.foodlover.data.model.category.*
import kotlinx.android.synthetic.main.item_categories.view.*

class AllCategoryAdapter : BaseAdapter<Category>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_all_categories,
                    parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.itemView.apply {
            cl_itemCategory.setBackgroundColor(Color.parseColor(category.color))
            img_category.setImageResource(category.image)
            txt_category_title.text = category.title
            txt_category_places.text = category.places.toString() + " مکان "
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(category)
                }
            }


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}