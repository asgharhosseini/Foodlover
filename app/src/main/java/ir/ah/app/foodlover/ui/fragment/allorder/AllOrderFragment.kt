package ir.ah.app.foodlover.ui.fragment.allorder

import android.*
import android.os.*
import android.view.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.ui.adapter.*
import ir.ah.app.foodlover.ui.fragment.restaurant.*
import kotlinx.android.synthetic.main.fragment_all_order.*
import pub.devrel.easypermissions.*
import javax.inject.*

@AndroidEntryPoint
class AllOrderFragment :
    BaseFragment<RestaurantViewModel>(R.layout.fragment_all_order, RestaurantViewModel::class),
    OrderAdapter.OrderEventListener, OnMapReadyCallback, EasyPermissions.PermissionCallbacks {

    @Inject
    lateinit var orderAdapter: OrderAdapter
    var map: GoogleMap? = null
    private val arg by navArgs<AllOrderFragmentArgs>()

    @Inject
    lateinit var userInfoManager: UserInfoManager

    private var restaurantLatLng: LatLng? = null
    private var userLatLng: LatLng? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        requestPermissions()
        initView()
    }

    private fun initView() {
        restaurantLatLng = LatLng(arg.latitude!!.toDouble(), arg.longitude!!.toDouble())
        userLatLng = LatLng(
            userInfoManager.getLatitude()!!.toDouble(),
            userInfoManager.getLongitude()!!.toDouble()
        )

        txt_fragmentOrder_gift.text = NumberHelper.EnglishToPersian("0") + "%"
        txt_allOrderFragment_userLocationName.text = userInfoManager.getLocationName()

        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24)


        subscribeToObservers()
        setUpRecyclerViews()
        onClick()
        setupMap()

    }

    private fun setupMap() {

        mapView.getMapAsync(this)

    }


    private fun onClick() {
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        txt_allOrderFragment_editProfile.setOnClickListener {
            findNavController().navigate(AllOrderFragmentDirections.actionAllOrderFragmentToProfileFragment())
        }
    }

    private fun subscribeToObservers() {
        viewModel.allOrderItems.observe(viewLifecycleOwner, Observer {
            orderAdapter.differ.submitList(it)
        })
        viewModel.totalSum.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                txt_fragmentOrder_totalPrice.text =
                    NumberHelper.EnglishToPersian(it.toString()) + "تومان"
            } else {
                txt_fragmentOrder_totalPrice.text =
                    NumberHelper.EnglishToPersian("0") + "تومان"
                findNavController().popBackStack()
            }

        })
    }


    private fun setUpRecyclerViews() {
        rv_fragmentAllOrder.apply {
            adapter = orderAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        orderAdapter.setOnOrderItemEventListener(this)

    }


    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onDelete(order: Order, position: Int) {
        viewModel.deleteOrderItem(order)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map!!.addMarker(MarkerOptions().position(userLatLng).title("آدرس شما"))
        map!!.addMarker(MarkerOptions().position(restaurantLatLng).title("محل رستوران"))
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 14f))


    }

    private fun requestPermissions() {
        if (Utility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                Constance.REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                Constance.REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        setupMap()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


}

