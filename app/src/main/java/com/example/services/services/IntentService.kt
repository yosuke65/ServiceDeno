package com.example.services.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyIntentService : IntentService("mythread") {
    companion object{
        private const val TAG = "MyIntentService"
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")
        Toast.makeText(this,"Intent Service onHandleIntnet", Toast.LENGTH_SHORT).show()
        for(i in 1..10){
            Thread.sleep(1000)
            Log.d(TAG, "onHandleIntent: $i")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Intent Service created", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onCreate")
        }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this,"Intent Service Started", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Task destroyed")
        Toast.makeText(this,"Intent Service Destroyed", Toast.LENGTH_SHORT).show()

    }
}