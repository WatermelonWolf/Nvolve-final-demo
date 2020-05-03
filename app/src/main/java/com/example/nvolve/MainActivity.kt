package com.example.nvolve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoginButton.setOnClickListener {
            //get text from edittexts
            val Username = Usernameinput.text.toString()
            val Password = Passwordinput.text.toString()

            val str = Usernameinput.text.toString()
            val str2 = Passwordinput.text.toString()

            //intent to start activity
            val intent = Intent(this@MainActivity, UserScreen::class.java)
            if(str=="apetrie"&&str2=="cleverpassword") {
                intent.putExtra("Username", Username)
                intent.putExtra("Password", Password)
                startActivity(intent)
            }
            else
                Errortext.text="Error:Invalid Username or Password. Try Again"
        }
    }
}
