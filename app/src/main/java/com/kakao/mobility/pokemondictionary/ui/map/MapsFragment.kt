package com.kakao.mobility.pokemondictionary.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kakao.mobility.pokemondictionary.R
import com.kakao.mobility.pokemondictionary.data.LocationData

class MapsFragment : Fragment() {

    companion object {
        private const val ARG_LOCATIONS = "ARG_LOCATIONS"
        fun newInstance(locations:ArrayList<LocationData>) = MapsFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARG_LOCATIONS,locations)
            }
        }

    }

    private lateinit var mMap: GoogleMap

    private val locations
        get() = arguments?.getParcelableArrayList<LocationData>(ARG_LOCATIONS)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.findFragmentById(R.id.map).apply {
            this as SupportMapFragment
            getMapAsync {
                    googleMap ->  mMap = googleMap

                locations?.forEach {
                    mMap.addMarker(MarkerOptions().position(LatLng(it.lat,it.lng)).title("Marker in Sydney"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(it.lat,it.lng)))
                }
            }
        }
    }
}