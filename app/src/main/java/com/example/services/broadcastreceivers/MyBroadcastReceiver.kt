package com.example.services.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.JobIntentService
import com.example.services.services.MyJobIntentService


abstract class MyBroadcastReceiver(): BroadcastReceiver() {

    override fun onReceive(context:Context?, intent: Intent?) {
        var message = intent?.getStringExtra(MyJobIntentService.KEY_COMP)
        if (!message.isNullOrEmpty()){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
        val count = intent?.getIntExtra(MyJobIntentService.KEY,0)
        setCountView(count)
    }

    abstract fun setCountView(count:Int?)
}