package com.example.services.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.services.R
import com.example.services.services.MyIntentService
import com.example.services.services.MyService
import kotlinx.android.synthetic.main.activity_my_intent.*

class IntentServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_intent)

        init()
    }

    private fun init() {
        button_start_intent.setOnClickListener {

            var myIntent = Intent(this, MyIntentService::class.java)
            startService(myIntent)

        }
    }

}