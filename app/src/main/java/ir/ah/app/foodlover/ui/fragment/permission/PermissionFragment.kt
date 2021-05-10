package ir.ah.app.foodlover.ui.fragment.permission

import android.*
import android.annotation.*
import android.location.*
import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.*
import com.google.android.material.snackbar.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.*
import kotlinx.android.synthetic.main.fragment_permission.*
import pub.devrel.easypermissions.*
import java.util.*
import javax.inject.*

@AndroidEntryPoint
class PermissionFragment : Fragment(R.layout.fragment_permission),
    EasyPermissions.PermissionCallbacks {
    @Inject
    lateinit var userInfoManager: UserInfoManager

    private var gMap: GoogleMap? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var latLng: LatLng? = null

    private var geocoder: Geocoder? = null
    private var addressList: List<Address> = arrayListOf()
    private var cityName = ""
    private var address = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map_container.onCreate(savedInstanceState)
        initView()
        initRequestMap()

    }


    private fun initView() {
        requestPermissions()

        btn_addLocationPermission.setOnClickListener {
            requestPermissions()

            val mainThreadHandler = Handler(Looper.getMainLooper())

            mainThreadHandler.postDelayed({
                try {
                    findNavController().navigate(
                        PermissionFragmentDirections.actionPermissionFragmentToHomeFragment()
                    )
                } catch (e: Exception) {
                    Snackbar.make(requireView(), e.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }, 1000)


        }
        btn_addLocationPermissionLater.setOnClickListener {
            findNavController().navigate(PermissionFragmentDirections.actionPermissionFragmentToHomeFragment())
        }
    }

    @SuppressLint("MissingPermission")
    private fun initRequestMap() {
        requestPermissions()
//       val mapFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        val task: Task<Location> = fusedLocationProviderClient!!.lastLocation
        task.addOnSuccessListener {
            if (it != null) {
                latLng = LatLng(it.latitude, it.longitude)
                geocoder = Geocoder(requireContext(), Locale.getDefault())
                addressList = geocoder!!.getFromLocation(it.latitude, it.longitude, 1)
                cityName = addressList.get(0).getAddressLine(0)
                address = "$cityName "

                map_container.getMapAsync(object : OnMapReadyCallback {
                    override fun onMapReady(googleMap: GoogleMap) {
                        gMap = googleMap
                        gMap?.setOnMapClickListener {
                            latLng = LatLng(it.latitude, it.longitude)
                            geocoder = Geocoder(requireContext(), Locale.getDefault())
                            addressList = geocoder!!.getFromLocation(it.latitude, it.longitude, 1)
                            cityName = addressList.get(0).getAddressLine(0)
                            address = "$cityName "
                            gMap!!.clear()
                            gMap!!.addMarker(
                                MarkerOptions()
                                    .position(it)
                                    .title(cityName + "آدرش شما:")
                            )

                            userInfoManager.saveLocation(
                                latLng!!.latitude,
                                latLng!!.longitude,
                                cityName
                            )

                        }

                        gMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f))
                        gMap?.addMarker(
                            MarkerOptions().position(latLng).title("شما").snippet("مکان شما")
                        )
                        userInfoManager.saveLocation(latLng!!.latitude, latLng!!.longitude, address)

                    }

                })


            }

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