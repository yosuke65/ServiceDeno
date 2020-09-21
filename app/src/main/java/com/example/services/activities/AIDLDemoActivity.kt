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
import com.example.services.aidl.IMyAidlInterface
import com.example.services.services.MyAIDLDemoService
import kotlinx.android.synthetic.main.activity_a_i_d_l_demo.*

class AIDLDemoActivity : AppCompatActivity() {

    private lateinit var mService:IMyAidlInterface
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_i_d_l_demo)

        init()
    }

    private fun init() {

        button_add.setOnClickListener{
            add()
        }

        val mIntent = Intent(this,MyAIDLDemoService::class.java)

        var serviceConnection = object:ServiceConnection{
            override fun onServiceDisconnected(componetName: ComponentName?) {
                isConnected = false
            }

            override fun onServiceConnected(conponentName: ComponentName?, iBinder: IBinder?) {
                isConnected = true
                mService = IMyAidlInterface.Stub.asInterface(iBinder)
            }

        }
        bindService(mIntent,serviceConnection,Context.BIND_AUTO_CREATE)
    }

    private fun add(){
        val x = edit_text_x.text.toString().toInt()
        val y = edit_text_y.text.toString().toInt()
        val result = mService.add(x,y)
        text_view_result.text = result.toString()
    }
}