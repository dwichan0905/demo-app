package com.vikiwahyudi.deteksigempadantsunami.ui.home


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.Style
import com.vikiwahyudi.deteksigempadantsunami.databinding.FragmentHomeBinding
import com.vikiwahyudi.deteksigempadantsunami.ui.settings.Buffer
import com.vikiwahyudi.deteksigempadantsunami.viewmodel.ViewModelFactory
import kotlin.math.roundToInt


class HomeFragment : Fragment() {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val fragmentHomeBinding get() = _fragmentHomeBinding!!

    private val LOCATION_PERMISSION_REQ_CODE = 1000;
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private var distanceInKilometers: Int = 0

    private var potensi = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity().applicationContext)

        childFragmentManager.executePendingTransactions()

        getCurrentLocation()

        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            viewModel.getGempaTerkini().observe(viewLifecycleOwner, { gempaTerkini ->
                showProgressBar(false)
                with(fragmentHomeBinding.detailGempa) {
                    tvTanggal.text = gempaTerkini?.tanggal
                    tvJam.text = gempaTerkini?.jam
                    tvMagnitude.text = gempaTerkini?.magnitude
                    tvDepth.text = gempaTerkini?.kedalaman
                    tvCoordinate.text = gempaTerkini?.lintang + " " + gempaTerkini?.bujur
                    tvWilayah.text = gempaTerkini?.wilayah
                    tvPotensiOrDirasakan.text = gempaTerkini?.prediksi
                    tvCoordinateUser.text = ("${latitude} LS ${longitude} BT").toString()
                    tvJarak.text = ("${distanceInKilometers} km").toString()
                }
                val coordinates = gempaTerkini?.coordinates?.split(",")
                val Lat = coordinates?.get(0)?.toDouble()
                val Lng = coordinates?.get(1)?.toDouble()

                potensi = gempaTerkini?.prediksi.toString()

                savedInstanceState?.let { fragmentHomeBinding.mapView.onSaveInstanceState(it) }
                Lat?.let { Lng?.let { it1 -> setMap(it, it1) } }

                Lat?.let { Lng?.let { it1 -> distanceLocation(it, it1) } }
            })
        }
    }


    private fun setMap(lat: Double, lng: Double) {
        fragmentHomeBinding.mapView.getMapAsync {
            it.setStyle(Style.TRAFFIC_DAY)

            val location = LatLng(lat, lng)
            val position = CameraPosition.Builder()
                .target(location)
                .zoom(6.0)
                .bearing(10.0)
                .tilt(10.0)
                .build()

            it.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1000)
            it.addMarker(MarkerOptions().setPosition(location))
        }
    }

    private fun showProgressBar(state: Boolean) {
        fragmentHomeBinding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }


    private fun getCurrentLocation() {
        // checking location permission
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE
            )
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    latitude = location.latitude
                }

                if (location != null) {
                    longitude = location.longitude
                }
            }
    }

    private fun distanceLocation(lat: Double, lng: Double) {
        val location1 = LatLng(lat, lng)
        val location2 = LatLng(latitude, longitude)
        val distanceInMeters = location1.distanceTo(location2).toFloat()
        distanceInKilometers = (distanceInMeters / 1000).roundToInt()
        Log.i("distance", "${distanceInKilometers}")
        if (potensi == "Berpotensi tsunami" && distanceInKilometers < 20) {
            fragmentHomeBinding.detailGempa.apply {
                tvTerdampak.setText("Terdampak tsunami")
            }
        } else {
            fragmentHomeBinding.detailGempa.apply {
                tvTerdampak.setText("Tidak terdampak tsunami")
            }
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // permission granted
                } else {
                    // permission denied
                    Toast.makeText(
                        activity, "You need to grant permission to access location",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        val buffer = Buffer()
    }

    // lifecycle
    override fun onStart() {
        super.onStart()
        fragmentHomeBinding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        fragmentHomeBinding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        fragmentHomeBinding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        fragmentHomeBinding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentHomeBinding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        fragmentHomeBinding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentHomeBinding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentHomeBinding.mapView.onDestroy()
    }
}