package ir.ah.app.foodlover.ui.adapter


import android.graphics.*
import android.view.*
import com.bumptech.glide.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.banner.*
import kotlinx.android.synthetic.main.item_banner.view.*
import javax.inject.*

class BannerAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Banner>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_banner,
                    parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val banner = differ.currentList[position]
        holder.itemView.apply {
            txt_homeFragment_bannerMore.text = banner.title
            txt_itemBanner_subtitle.text = banner.subtitle
            ll_itemBanner.setBackgroundColor(Color.parseColor(banner.color))
            txt_itemBanner_discount.text = banner.discount + "% تخفیف "
            txt_itemBanner_cod.text = banner.code + ":کد تخفیف"

            glide.load(banner.image).into(img_itemBanner)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}