package com.example.labex5_locationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity(), LocationListener {

    var latitude by mutableStateOf("Waiting for GPS...")
    var longitude by mutableStateOf("Waiting for GPS...")
    lateinit var locationManager: LocationManager

    // popup that asks the user for permission
    val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            startLocation()
        } else {
            latitude = "Permission denied"
            longitude = "Permission denied"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Latitude: $latitude", fontSize = 22.sp)
                Text(text = "Longitude: $longitude", fontSize = 22.sp)
            }
        }

        // check if we already have permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            startLocation()
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocation() {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000L,
            0f,
            this
        )
    }

    // called every time the GPS gives a new location
    override fun onLocationChanged(location: Location) {
        latitude = location.latitude.toString()
        longitude = location.longitude.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(this)
    }
}