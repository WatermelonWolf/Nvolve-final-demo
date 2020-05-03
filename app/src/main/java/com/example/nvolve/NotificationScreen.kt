package com.example.nvolve

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_notification_screen.*

class NotificationScreen : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "com.example.notifications"
    private val description = "My Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_screen)
        val show =findViewById<Button>(R.id.PushNotif)
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager

        val StudentInvNot1 = arrayOf("General Body Meeting", "4/28/2020  - Meeting at Old Falvey, Remember to bring write-up of charity organization suggestions")
        val StudentInvNot2 = arrayOf("Augustine Fundraiser", "5/12/2020 - Meet on south campus parking lot to take the bus over to Blue Bell Church")
        val PrideNot1 = arrayOf("Annual Pride Festival", "4/30/2020 - Pride Festival occurring in Philly, Student vans available or you can take the Septa")
        val PrideNot2 = arrayOf("Meeting Canceled", "5/1/2020 - No meeting due to scheduling conflicts")
        val POWERNot1 = arrayOf("General Body Meeting", "4/28/2020 - Meeting at 3rd floor Bartley Hall 8:00pm")
        val POWERNot2 = arrayOf("Bake Sale", "4/26/2020 - Swing by the Cova Entrance to show support to our members!")
        val BlueKeyNot1 = arrayOf("Campus Tour Training", "5/14/2020 - Practice tours, meeting outside of Connely, 11:00am")
        val BlueKeyNot2 = arrayOf("New Student Welcoming", "5/20/2020 - Occurring outside of the Pavillion at 1:00pm, Bring Villanova Clothes and smiles")
        var id = 1


        //Currently set to activate a push notification one at a time
        show.setOnClickListener {
            val intent=Intent(applicationContext,NotificationScreen::class.java)
            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor=Color.RED
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                if (id==1){
                    builder=Notification.Builder(this,channelId)
                        .setContentTitle(FirstTitle.text.toString())
                        .setContentText(FirstDescription.text.toString())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentIntent(pendingIntent)
                    id--
                }
                else {
                    builder = Notification.Builder(this, channelId)
                        .setContentTitle(SecondTitle.text.toString())
                        .setContentText(SecondDescription.text.toString())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentIntent(pendingIntent)
                    id++
                }


            }
            else{
                if (id==1){
                    builder=Notification.Builder(this)
                        .setContentTitle(FirstTitle.text.toString())
                        .setContentText(FirstDescription.text.toString())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentIntent(pendingIntent)
                    id--
                }
                else {
                    builder = Notification.Builder(this)
                        .setContentTitle(SecondTitle.text.toString())
                        .setContentText(SecondDescription.text.toString())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentIntent(pendingIntent)
                    id++
                }
            }
            notificationManager.notify(id,builder.build())

        }

        spinner = findViewById(R.id.spinner) as Spinner

        val options = arrayOf("Office of Student Involvement", "POWER", "Pride", "Blue Key Society")

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                if (options.get(position).equals("Office of Student Involvement")){
                    FirstTitle.text=""+StudentInvNot1[0]
                    FirstDescription.text=""+StudentInvNot1[1]
                    SecondTitle.text=""+StudentInvNot2[0]
                    SecondDescription.text=""+StudentInvNot2[1]
                }
                if (options.get(position).equals("Pride")){
                    FirstTitle.text=""+PrideNot1[0]
                    FirstDescription.text=""+PrideNot1[1]
                    SecondTitle.text=""+PrideNot2[0]
                    SecondDescription.text=""+PrideNot2[1]
                }
                if (options.get(position).equals("POWER")){
                    FirstTitle.text=""+POWERNot1[0]
                    FirstDescription.text=""+POWERNot1[1]
                    SecondTitle.text=""+POWERNot2[0]
                    SecondDescription.text=""+POWERNot2[1]
                }
                if (options.get(position).equals("Blue Key Society")){
                    FirstTitle.text=""+BlueKeyNot1[0]
                    FirstDescription.text=""+BlueKeyNot1[1]
                    SecondTitle.text=""+BlueKeyNot2[0]
                    SecondDescription.text=""+BlueKeyNot2[1]
                }
            }
        }
        Leave.setOnClickListener {
            FirstTitle.text=""
            FirstDescription.text=""
            SecondTitle.text=""
            SecondDescription.text=""
        }
     }

}
