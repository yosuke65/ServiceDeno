package com.example.services.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class MyLocalBoundService : Service() {

    private var mRandomNumber:Int = 0
    private var mIsRandomGeneratorOn = false
    companion object {
        const val TAG = "MyBoundService"
        const val MAX = 100
        const val MIN = 0
    }

    inner class MyBinder : Binder() {
        fun getMyBoundService() = this@MyLocalBoundService

    }

    override fun onCreate() {
        Log.v(TAG, "onCreate: ${Thread.currentThread().name}")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(TAG, "onStartCommand: ${Thread.currentThread().name}")
        super.onStartCommand(intent, flags, startId)
        mIsRandomGeneratorOn = true
        Thread(Runnable {
            startRandomNumberGenerator()
        }).also {
            it.name = "Another thread"
            it.start() }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.v(TAG, "onBind: ${Thread.currentThread().name}")
        return MyBinder()
    }

    override fun onRebind(intent: Intent?) {
        Log.v(TAG, "onRebind: ${Thread.currentThread().name}")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(TAG, "onUnbind: ${Thread.currentThread().name}")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.v(TAG, "onDestroy: ${Thread.currentThread().name}")
        stopRandomNumberGenerator()
        super.onDestroy()
    }

    private fun startRandomNumberGenerator() {
        while(mIsRandomGeneratorOn){
            try{
                Thread.sleep(1000)
                if(mIsRandomGeneratorOn){
                    mRandomNumber = Random().nextInt(MAX) + MIN
                    Log.v(TAG,"Thread name: ${Thread.currentThread().name} + Random Number : $mRandomNumber")
                }else{
                    Log.v(TAG,"JobIntentService stopped")
                    return
                }
            }catch (e:InterruptedException){
                Log.e(TAG,"Thread Interrupted")
            }
        }
    }

    private fun stopRandomNumberGenerator(){
        mIsRandomGeneratorOn = false
    }

    fun getRandomNumber() = mRandomNumber

}


