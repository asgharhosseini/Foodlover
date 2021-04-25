package ir.ah.app.foodlover.ui.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.category.Category
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoryAdapter : BaseAdapter<Category>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_categories,
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