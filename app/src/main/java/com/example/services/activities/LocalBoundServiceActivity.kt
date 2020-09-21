package com.example.services.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.services.R
import com.example.services.services.MyLocalBoundService
import kotlinx.android.synthetic.main.activity_bound_service.*

class LocalBoundServiceActivity : AppCompatActivity(), View.OnClickListener {
    private var connection: ServiceConnection? = null
    private lateinit var boundServiceIntent: Intent
    private var myBoundService: MyLocalBoundService? = null
    private var isServiceBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)

        init()
    }

    private fun init() {
        button_start_bound.setOnClickListener(this)
        button_stop_bound.setOnClickListener(this)
        button_bind.setOnClickListener(this)
        button_unbind.setOnClickListener(this)
        button_get_random_num.setOnClickListener(this)
        boundServiceIntent = Intent(this, MyLocalBoundService::class.java)
    }


    override fun onClick(view: View?) {
        when (view) {
            button_start_bound -> startService(boundServiceIntent)
            button_stop_bound -> stopService(boundServiceIntent)
            button_bind -> bindService()
            button_unbind -> unbindService()
            button_get_random_num -> getRandomNumber()
        }
    }


    private fun bindService() {
        if(connection == null){
            connection = object : ServiceConnection {
                override fun onServiceDisconnected(connectionName: ComponentName?) {
                    isServiceBound = false
                }

                override fun onServiceConnected(connectionName: ComponentName?, service: IBinder?) {
                    isServiceBound = true
                    val myBinder = service as MyLocalBoundService.MyBinder
                    myBoundService = myBinder.getMyBoundService()
                }
            }
        }
        val flags = Context.BIND_AUTO_CREATE
        bindService(boundServiceIntent, connection!!, flags)
    }

    private fun unbindService() {
        if(isServiceBound){
            unbindService(connection!!)
            isServiceBound = false
        }
    }

    private fun getRandomNumber() {
        if(isServiceBound){
            text_view.text = "Random Number: ${myBoundService?.getRandomNumber()}"
        }else{
            text_view.text = "Service is not found"
        }
    }

}