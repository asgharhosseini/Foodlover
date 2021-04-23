package ir.ah.app.foodlover.ui.fragment.Splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
@AndroidEntryPoint
class SplashFragment:Fragment(R.layout.fragment_spalsh) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainThreadHandler= Handler(Looper.getMainLooper())
        mainThreadHandler.postDelayed({
            try {
//                checkSituations()
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroitFragment())

            }catch (e:Exception){
                Snackbar.make(requireView(),e.message.toString(),Snackbar.LENGTH_LONG).show()
            }
        },2000)

    }
//    fun checkSituations() {
////        val networkAvailable = NetworkUtils.isNetworkAvailable(requireActivity())
//        if (!networkAvailable) {
////            Log.i(Constance.TAG,"No Connection")
//            // Do somethings
//        } else {
//            Log.i(Constance.TAG,"Connected to internet ")
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
//
//            /*if (TextUtils.isEmpty(TokenContainer.token)) {
//                DebugHelper.info("Token Invalid")
//                findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
//            } else {
//                DebugHelper.info("Everything is ok, Go to dashboard")
//                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
//            }*/
//        }
//    }
}