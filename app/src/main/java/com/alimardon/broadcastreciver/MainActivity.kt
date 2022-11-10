package com.alimardon.broadcastreciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Connection
import android.widget.Toast
import com.alimardon.broadcastreciver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(CONNECTIVITY_ACTION)
        registerReceiver(broadcast, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcast)
    }
     val broadcast = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent?.action) {
                val noconnect = intent.getBooleanExtra(EXTRA_NO_CONNECTIVITY, false)
                if (noconnect) {
                    binding.textView.text = "yo'q"
                } else {
                    binding.textView.text = "bor"
                }
            }
        }
    }
}