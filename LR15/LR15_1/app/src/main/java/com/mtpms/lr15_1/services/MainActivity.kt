package com.mtpms.lr15_1.services

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mtpms.lr15_1.stpms.services.R
import com.mtpms.lr15_1.services.services.MyLocationService
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE: Int = 101
const val ACTION = "com.mtpms.lr15_1.services"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestServicesWithPermissionCheck()
    }

    private fun requestServicesWithPermissionCheck() {

        val permissions = arrayListOf(
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION),
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION),
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        )

        if (permissions.any { it != PackageManager.PERMISSION_GRANTED }) {

            requestPermissions(
                    arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    REQUEST_CODE
            )

        } else {
            requestLocation()
        }
    }

    private fun requestLocation() {
        button_start.setOnClickListener {
            val service = Intent(this, MyLocationService::class.java)
            ContextCompat.startForegroundService(this, service)
        }
        button_stop.setOnClickListener {
            val service = Intent(this, MyLocationService::class.java)
            stopService(service)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation()
                }
            }

        }
    }



    private val receiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast
                    .makeText(this@MainActivity, p1?.getStringExtra("success"), Toast.LENGTH_SHORT)
                    .show()
        }

    }

    private val filter = IntentFilter(ACTION)

    override fun onResume() {
        registerReceiver(receiver, filter)
        super.onResume()
    }

    override fun onPause() {
        unregisterReceiver(receiver)
        super.onPause()
    }
}