package ir.ah.app.foodlover.ui.fragment.permission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.ACCESS_COARSE_LOCATION
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.ACCESS_FINE_LOCATION
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.READ_EXTERNAL_STORAGE
import ir.ah.app.foodlover.other.checkpermissions.PermissionCheck
import kotlinx.android.synthetic.main.fragment_permission.*

class PermissionFragment:Fragment(R.layout.fragment_permission) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btn_addLocationPermission.setOnClickListener {
            PermissionCheck.checkPermissions(requireActivity(), intArrayOf(  ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION))
            if (PermissionCheck.checkPermissions(requireActivity(), intArrayOf(
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION
                ))) {
                findNavController().navigate(PermissionFragmentDirections.actionPermissionFragmentToHomeFragment())
            }
        }
        btn_addLocationPermissionLater.setOnClickListener {
            findNavController().navigate(PermissionFragmentDirections.actionPermissionFragmentToHomeFragment())
        }
    }
}