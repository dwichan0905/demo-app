package com.vikiwahyudi.deteksigempadantsunami.ui.detailgempa

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.Style
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.MagnitudoEntity
import com.vikiwahyudi.deteksigempadantsunami.databinding.ActivityDetailGempaBinding
import kotlin.math.roundToInt

class DetailGempaActivity : AppCompatActivity() {

    private var _activityDetailGempa: ActivityDetailGempaBinding? = null
    private val activityDetailGempaBinding get() = _activityDetailGempa!!

    private val LOCATION_PERMISSION_REQ_CODE = 1000;
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private var distanceInKilometers: Int = 0
    private var distanceInKilometersDirasakan: Int = 0

    private var potensiMagnitudo = ""
    private var potensiDirasakan = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.access_token))
        _activityDetailGempa = ActivityDetailGempaBinding.inflate(layoutInflater)
        setContentView(activityDetailGempaBinding.root)
        supportActionBar?.title = "Detail Gempa"
        supportActionBar?.setDisplayShowHomeEnabled(true)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        savedInstanceState?.let { activityDetailGempaBinding.mapView.onSaveInstanceState(it) }

        getCurrentLocation()

        val fromActivityMagnitudo = intent.getBooleanExtra(EXTRA_FROM_MAGNITUDE, false)
        if (fromActivityMagnitudo) {
            val gempaMagnitudo = intent.getParcelableExtra<MagnitudoEntity>(EXTRA_DETAIL_GEMPA)
            val coordinatesMagnitudo = gempaMagnitudo?.coordinates?.split(",")
            val Lat = coordinatesMagnitudo?.get(0)?.toDouble()
            val Lng = coordinatesMagnitudo?.get(1)?.toDouble()
            setMap(Lat, Lng)
            distanceLocationMagnitudo(Lat, Lng)
            with(activityDetailGempaBinding.detailGempa) {
                tvTanggal.text = gempaMagnitudo?.tanggal
                tvJam.text = gempaMagnitudo?.jam
                tvMagnitude.text = gempaMagnitudo?.magnitude
                tvDepth.text = gempaMagnitudo?.kedalaman
                tvCoordinate.text = gempaMagnitudo?.lintang + " " + gempaMagnitudo?.bujur
                tvWilayah.text = gempaMagnitudo?.wilayah
                tvPotensiOrDirasakan.text = gempaMagnitudo?.prediksi
                tvJarak.text = ("${distanceInKilometers} km").toString()
            }

            potensiMagnitudo = gempaMagnitudo?.prediksi.toString()

        } else {
            val gempaDirasakan = intent.getParcelableExtra<DirasakanEntity>(EXTRA_DETAIL_GEMPA)
            val coordinatesDirasakan = gempaDirasakan?.coordinates?.split(",")
            val Lat = coordinatesDirasakan?.get(0)?.toDouble()
            val Lng = coordinatesDirasakan?.get(1)?.toDouble()
            setMap(Lat, Lng)
            distanceLocationDirasakan(Lat, Lng)
            with(activityDetailGempaBinding.detailGempa) {
                tvTanggal.text = gempaDirasakan?.tanggal
                tvJam.text = gempaDirasakan?.jam
                tvMagnitude.text = gempaDirasakan?.magnitude
                tvPotensiOrDirasakan.text = gempaDirasakan?.prediksi
                tvDepth.text = gempaDirasakan?.kedalaman
                tvCoordinate.text = gempaDirasakan?.lintang + " " + gempaDirasakan?.bujur
                tvWilayah.text = gempaDirasakan?.wilayah
                tvJarak.text = ("${distanceInKilometersDirasakan} km").toString()

            }

            potensiDirasakan = gempaDirasakan?.prediksi.toString()

        }
    }


    private fun setMap(lat: Double?, lng: Double?) {
        activityDetailGempaBinding.mapView.getMapAsync {
            it.setStyle(Style.TRAFFIC_DAY)

            val location = lat?.let { it1 -> lng?.let { it2 -> LatLng(it1, it2) } }
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

    private fun getCurrentLocation() {
        // checking location permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request permission
            ActivityCompat.requestPermissions(
                this,
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
                distanceLocationMagnitudo(latitude, longitude)
                distanceLocationDirasakan(latitude, longitude)
            }
            .addOnFailureListener {
                Toast.makeText(
                    this, "Failed on getting current location",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun distanceLocationMagnitudo(lat: Double?, lng: Double?) {
        val location1 = lat?.let { lng?.let { it1 -> LatLng(it, it1) } }
        val location2 = LatLng(latitude, longitude)
        val distanceInMeters = location1!!.distanceTo(location2).toFloat()
        distanceInKilometers = (distanceInMeters / 1000).roundToInt()
        val distanceInKilometers = distanceInKilometers
        activityDetailGempaBinding.detailGempa.apply {
            tvCoordinateUser.text = ("${latitude} LS ${longitude} BT").toString()
        }
        if (potensiMagnitudo == "Berpotensi tsunami" && distanceInKilometers < 20) {
            activityDetailGempaBinding.detailGempa.apply {
                tvTerdampak.setText("Terdampak tsunami")
            }
        } else {
            activityDetailGempaBinding.detailGempa.apply {
                tvTerdampak.setText("Tidak terdampak tsunami")
            }
        }

    }


    private fun distanceLocationDirasakan(lat: Double?, lng: Double?) {
        val location1 = lat?.let { lng?.let { it1 -> LatLng(it, it1) } }
        val location2 = LatLng(latitude, longitude)
        val distanceInMeters = location1!!.distanceTo(location2).toFloat()
        distanceInKilometersDirasakan = (distanceInMeters / 1000).roundToInt()
        Log.i("distanceDirasakan", "${distanceInKilometersDirasakan}")
        activityDetailGempaBinding.detailGempa.apply {
            tvCoordinateUser.text = ("${latitude} LS ${longitude} BT").toString()
        }
        if (potensiDirasakan == "Berpotensi tsunami" && distanceInKilometers < 20) {
            activityDetailGempaBinding.detailGempa.apply {
                tvTerdampak.setText("Terdampak tsunami")
            }
        } else {
            activityDetailGempaBinding.detailGempa.apply {
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
                        this, "You need to grant permission to access location",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    companion object {
        const val EXTRA_DETAIL_GEMPA = "extra_detail_gempa"
        const val EXTRA_FROM_MAGNITUDE = "extra_from_magnitude"
    }

    // lifecycle
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        activityDetailGempaBinding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        activityDetailGempaBinding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        activityDetailGempaBinding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        activityDetailGempaBinding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityDetailGempaBinding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        activityDetailGempaBinding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityDetailGempaBinding.mapView.onDestroy()
        _activityDetailGempa = null
    }


}