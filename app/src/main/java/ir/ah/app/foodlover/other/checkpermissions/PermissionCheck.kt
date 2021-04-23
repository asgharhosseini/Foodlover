package ir.ah.app.foodlover.other.checkpermissions
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionCheck: AppCompatActivity(){
    companion object{
        fun checkPermissions(activity: Activity?, permissionCheck: IntArray): Boolean {
            val permissionList: MutableList<String> = ArrayList()
            for (permission in permissionCheck) {
                if (ContextCompat.checkSelfPermission(activity!!, ConstantsPermissions.PERMISSIONS[permission - 1])
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionList.add(
                        ConstantsPermissions.PERMISSIONS[permission - 1]
                    )
                }
            }
            if (!permissionList.isEmpty()) {
                ActivityCompat.requestPermissions(activity!!, permissionList.toTypedArray(), 101)
                return false
            }
            return true
        }

    }
}