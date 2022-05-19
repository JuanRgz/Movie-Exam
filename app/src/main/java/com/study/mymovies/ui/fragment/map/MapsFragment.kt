package com.study.mymovies.ui.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.study.mymovies.R
import com.study.mymovies.core.location.FirebaseRepository

class MapsFragment : Fragment() {
    var mapReady: Boolean = false
    var Map: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        mapReady = true
        Map = googleMap
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        getLocations()
    }

    private fun getLocations(){
        FirebaseRepository().getLocation({
            //limpiamos la lista una vez notificada de algun cambio
            Map?.clear()
        }, { place ->
            //Agregamos marcadores uno a uno
            Map?.let{ map ->
                map.clear()
                val location = LatLng(place.latitud, place.longitud)
                map.addMarker(MarkerOptions().position(location))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16F))
            }
        })
    }
}