package com.example.nvolve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_screen.*

class UserScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_screen)

        NotificationButton.setOnClickListener {
            val intent = Intent(this@UserScreen, NotificationScreen::class.java)
            startActivity(intent)
        }

        ExploreButton.setOnClickListener {
            val intent = Intent(this@UserScreen, Explore::class.java)
            startActivity(intent)
        }

        ScanButton.setOnClickListener {
            val intent = Intent(this@UserScreen, QRCode::class.java)
            startActivity(intent)
        }
    }
}
