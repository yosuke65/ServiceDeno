package com.example.services.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.services.aidl.IMyAidlInterface

class MyAIDLDemoService : Service() {


    private val mBinder = object: IMyAidlInterface.Stub(){
        override fun add(x: Int, y: Int): Int {
            return x + y
        }
    }

    
    override fun onBind(intent: Intent): IBinder {
       return mBinder
    }


}
