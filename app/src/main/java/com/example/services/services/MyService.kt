package com.example.services.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {

    companion object{
        const val TAG = "MyService"
    }



    override fun onCreate() {
        Log.v(TAG, "onCreate: $this")
        super.onCreate()
    }

    //Start Service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(TAG,"onStartCommand: $this")
        val dataFromActivity = intent?.getStringExtra("KEY")
        Log.v(TAG,"${intent?.getStringExtra("KEY")}")


        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    //Bind Service
    override fun onBind(intent: Intent?): IBinder? {
        return null
        Log.v(TAG,"onBind")

    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(TAG,"onUnBind")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Log.v(TAG,"onReBind")
        super.onRebind(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG,"onDestroy: $this")

    }

}