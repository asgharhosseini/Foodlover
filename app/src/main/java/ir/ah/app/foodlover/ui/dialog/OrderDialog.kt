package ir.ah.app.foodlover.ui.dialog

import android.os.*
import android.view.*
import android.widget.*
import com.bumptech.glide.*
import com.bumptech.glide.load.engine.*
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.*
import com.google.android.material.bottomsheet.*
import com.google.android.material.button.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.other.*

class OrderDialog(
    private val order: Order,
    val onClicked: MethodBlock<Order> = {}
) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return LayoutInflater.from(requireContext()).inflate(R.layout.dialog_order, null, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.img_dialogOrder_image)
        val title = view.findViewById<TextView>(R.id.txt_dialogOrder_title)
        val price = view.findViewById<TextView>(R.id.txt_dialogOrder_price)
        val orderNumber = view.findViewById<TextView>(R.id.txt_dialogOrderCount)
        val btnBuy = view.findViewById<MaterialButton>(R.id.btn_dialogBuy)
        val btnAdd = view.findViewById<MaterialButton>(R.id.imageButton_add)
        val btnRemove = view.findViewById<MaterialButton>(R.id.imageButton_remove)
        var number = 1

        title.text = order.title
        price.text = order.price + " تومان"

        Glide.with(view.context).load(order.image)
            .placeholder(R.drawable.logobase)
            .error(R.drawable.logobase)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(image)

        btnBuy.setOnClickListener {
            onClicked(order)
            dismiss()
        }

        orderNumber.text = number.toString()
        btnAdd.setOnClickListener {
            number++
            orderNumber.text = number.toString()
        }
        btnRemove.setOnClickListener {
            if (number != 0) {
                number--
                orderNumber.text = number.toString()
            }

        }
    }

}

