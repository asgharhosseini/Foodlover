package ir.ah.app.foodlover.ui.fragment.profile

import android.*
import android.annotation.*
import android.location.*
import android.os.*
import android.text.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.*
import kotlinx.android.synthetic.main.fragment_profile.*
import pub.devrel.easypermissions.*
import java.util.*
import javax.inject.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile),
    EasyPermissions.PermissionCallbacks, OnMapReadyCallback {
    @Inject
    lateinit var userInfoManager: UserInfoManager

    private var gMap: GoogleMap? = null
    private var latLng: LatLng? = null

    private var cityName = ""
    private var address = ""
    private var fullName: String = "بدون نام "
    private var userAddress = "بدون آدرس"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map_container.onCreate(savedInstanceState)
        initView()
        initRequestMap()

    }


    private fun initView() {
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24)
        latLng = LatLng(
            userInfoManager.getLatitude()!!.toDouble(),
            userInfoManager.getLongitude()!!.toDouble()
        )
        address = userInfoManager.getAddress().toString()
        if (!userInfoManager.getFullName().isNullOrEmpty() && !userInfoManager.getAddress()
                .isNullOrEmpty()
        ) {
            fullName = userInfoManager.getFullName().toString()
            userAddress = userInfoManager.getAddress().toString()
            edt_nameText.text = Editable.Factory.getInstance().newEditable(fullName)
            edt_address.text = Editable.Factory.getInstance().newEditable(userAddress)
        }

        requestPermissions()
        onClick()


    }


    private fun onClick() {
        btn_save.setOnClickListener {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addressList = geocoder.getFromLocation(latLng!!.latitude, latLng!!.longitude, 1)
            cityName = addressList.get(0).getAddressLine(0)
            address = "$cityName "
            userInfoManager.saveLocation(latLng!!.latitude, latLng!!.longitude, address)

            if (edt_nameText.text.toString().isNotEmpty() &&
                edt_address.text.toString().isNotEmpty()
            ) {
                fullName = edt_nameText.text.toString()
                userAddress = edt_address.text.toString()
                userInfoManager.saveUserInfo(fullName, userAddress)
            } else {
                userInfoManager.saveUserInfo(fullName, userAddress)
            }
            findNavController().popBackStack()

        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("MissingPermission")
    private fun initRequestMap() {
        requestPermissions()
        map_container.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        gMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f))
        gMap?.addMarker(MarkerOptions().position(latLng).title("شما").snippet("مکان شما"))

        gMap?.setOnMapClickListener {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addressList = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            cityName = addressList.get(0).getAddressLine(0)
            address = "$cityName "
            latLng = LatLng(it.latitude, it.longitude)
            gMap!!.clear()
            gMap!!.addMarker(
                MarkerOptions()
                    .position(it)
                    .title(cityName + "آدرش شما:")
            )


        }


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
        initRequestMap()
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

    override fun onResume() {
        super.onResume()
        map_container?.onResume()
    }

    override fun onStart() {
        super.onStart()
        map_container?.onStart()

    }

    override fun onStop() {
        super.onStop()
        map_container?.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_container?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_container?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map_container?.onSaveInstanceState(outState)
    }


}