package com.example.nvolve

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qrcode.*
import android.R.attr.data
import android.widget.Toast
import com.google.zxing.integration.android.IntentResult
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View


class QRCode : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v:View?){
        when(v?.id){
            R.id.ScanButton ->{
                intentIntegrator.setBeepEnabled(true).initiateScan()
            }
        }
    }

    //QR Scanner fully functional and can scan any given QRCode, currently set to take the user to the POWER club page if a POWER QR code is scanned for testing purposes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        val queueSpot = "2"
        if (intentResult!=null){
            if (intentResult.contents != null){
                edQrCode.setText(intentResult.contents)

                val intent = Intent(this@QRCode, Explore::class.java)
                intent.putExtra("queueSpot", queueSpot)
                startActivity(intent)

            }else{
                Toast.makeText(this,"Dib",Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    lateinit var intentIntegrator: IntentIntegrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        intentIntegrator = IntentIntegrator(this)
        ScanButton.setOnClickListener(this)

        //Currently can take in any club name but is set to take the user to the Pride club when pride is typed in for testing purposes
        SearchButton.setOnClickListener {
            val SearchResult = SearchBar.text.toString()
            val intent = Intent(this@QRCode, Explore::class.java)
            intent.putExtra("SearchResult", SearchResult)
            startActivity(intent)
        }
    }


}
