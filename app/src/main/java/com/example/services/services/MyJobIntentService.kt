package com.example.services.services

import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import java.util.*

class MyJobIntentService: JobIntentService() {

    private var count:Int = 10

    companion object{
        const val ACTION ="com.example.services.services_myjobintentservice"
        const val TAG = "MyJobIntentService"
        const val KEY = "MyJobIntentService"
        const val KEY_COMP = "MyJobIntentService_comp"
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context,
                MyJobIntentService::class.java,56,intent)
        }
    }

    //Use for initializing you can create thread
    override fun onCreate() {
        Log.v(TAG, "onCreate: ${Thread.currentThread().name}")
        super.onCreate()
    }

    override fun onHandleWork(intent: Intent) {
        Log.v(TAG, "onHandleWork: ${Thread.currentThread().name}")
        startCountDown()
    }

    private fun startCountDown() {
        while(count >= 0){
            try{
                Thread.sleep(1000)
                if(count != 0){
                    var intent = Intent(ACTION)
                    intent.putExtra(KEY, count)
                    sendBroadcast(intent)
                    Log.v(TAG,"Thread id: ${Thread.currentThread().id} + Count: $count")
                }else{
                    var intent = Intent(ACTION)
                    intent.putExtra(KEY_COMP, "Service completed")
                    sendBroadcast(intent)
                    Log.v(TAG,"JobIntentService stopped")
                    return
                }
            }catch (e:InterruptedException){
                Log.e(TAG,"Thread Interrupted")
            }
            count--
        }
    }


    override fun onStopCurrentWork(): Boolean {
        Log.v(TAG, "onStopCurrentWork: ${Thread.currentThread().name}")

        return super.onStopCurrentWork()
    }


    override fun onDestroy() {
        Log.v(TAG, "onDestroy: ${Thread.currentThread().name}")
        super.onDestroy()
    }
}