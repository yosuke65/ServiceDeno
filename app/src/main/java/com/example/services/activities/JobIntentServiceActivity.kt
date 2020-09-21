package com.example.services.activities

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.services.R
import com.example.services.broadcastreceivers.MyBroadcastReceiver
import com.example.services.services.MyJobIntentService
import kotlinx.android.synthetic.main.activity_job_intent.*

class JobIntentServiceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var serviceIntent: Intent
    private lateinit var myBroadcastReceiver:MyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_intent)

        init()
    }

    override fun onStart() {
        super.onStart()
        var intentFilter =IntentFilter(MyJobIntentService.ACTION)
        registerReceiver(myBroadcastReceiver,intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
    }

    private fun init() {
        button_start.setOnClickListener(this)
        button_stop.setOnClickListener(this)
        serviceIntent = Intent(this, MyJobIntentService::class.java)

        myBroadcastReceiver = object: MyBroadcastReceiver(){
            override fun setCountView(count:Int?) {
                text_view_count.text = count?.toString()
            }

        }
    }

    override fun onClick(view: View?) {
        when (view) {
            button_start -> { startJobIntentServices()}
            button_stop -> {  stopService(serviceIntent);

            }
        }
    }



    private fun startJobIntentServices() {
//        startService(serviceIntent)
        MyJobIntentService.enqueueWork(
            this,
            serviceIntent
        )
    }
}