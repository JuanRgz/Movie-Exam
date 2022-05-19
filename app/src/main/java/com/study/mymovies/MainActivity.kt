package com.study.mymovies

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.study.mymovies.core.location.LocationService
import com.study.mymovies.core.location.LocationService.Companion.START
import com.study.mymovies.core.location.LocationService.Companion.STOP
import com.study.mymovies.databinding.ActivityMainBinding
import com.study.mymovies.ui.utils.checkingPermission
import com.study.mymovies.ui.utils.requestingPermission

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        startServiceLocation()
        /*val place = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
        Firebase.firestore.collection("place")
            .add(place)
            .addOnSuccessListener { documentReference ->
                Log.d(this::class.java.simpleName, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(this::class.java.simpleName, "Error adding document", e)
            }*/

        bind.apply {

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.moviesFragment,
                    R.id.mapsFragment,
                    R.id.uploadImageFragment,
                    R.id.usersFragment
                ),
                drawerLayout
            )

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)

            navView.setupWithNavController(navController)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        stopService(intent.apply { action = STOP })
    }

    private fun startServiceLocation(){
        if(checkingPermission()){
            val intent = Intent(
                this,
                LocationService::class.java
            )
            startService(intent.apply {action = START})
        } else{
            requestingPermission()
        }
    }
}