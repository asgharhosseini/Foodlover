package ir.ah.app.foodlover.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ah.app.foodlover.R
import kotlinx.android.synthetic.main.item_page.view.*


class IntroSliderAdapter : RecyclerView.Adapter<IntroSliderAdapter.PagerVH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    //get the size of color array
    override fun getItemCount(): Int = 3

    //binding the screen with view
    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        if(position == 0){
            fl_wave.setBackgroundResource(R.drawable.ic_intro1)
            tvTitle.text = "غذای مورد علاقت رو پیدا کن ..."
            tvSubtitle.text = " غدای مورد علاقت رو در هر زمانی و در هر مکانی پیدا کن ..."
            ivImage.setImageResource(R.drawable.img_intro1)

        }
        if(position == 1) {
            fl_wave.setBackgroundResource(R.drawable.ic_intro2)
            tvTitle.text = "تحویل گرم به خانه"
            tvSubtitle.text = "تحویل غدا در سریع ترین زمان ... "
            ivImage.setImageResource(R.drawable.img_intro2)

        }
        if(position == 2) {
            fl_wave.setBackgroundResource(R.drawable.ic_intro3)
            tvTitle.text = "غذای عالی برای شما "
            tvSubtitle.text = "درزمان کوتاه غذای عالی در یافت کنید."
            ivImage.setImageResource(R.drawable.img_intro3)

        }

    }


inner class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)

}
