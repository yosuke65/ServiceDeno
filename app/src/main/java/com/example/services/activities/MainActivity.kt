package com.example.services.activities

import android.content.*
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.services.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_bound_service.setOnClickListener(this)
        button_job_intent_service.setOnClickListener(this)
        button_service.setOnClickListener(this)
        button_intent_service.setOnClickListener(this)
        button_aidl_demo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            button_service -> startActivity(Intent(this, ServiceActivity::class.java))
            button_bound_service -> startActivity(Intent(this, LocalBoundServiceActivity::class.java))
            button_job_intent_service -> startActivity(Intent(this, JobIntentServiceActivity::class.java))
            button_intent_service -> startActivity(Intent(this, IntentServiceActivity::class.java))
            button_aidl_demo -> startActivity(Intent(this, AIDLDemoActivity::class.java))
        }
    }

}