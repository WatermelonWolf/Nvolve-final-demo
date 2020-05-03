package com.example.nvolve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_explore.*
import java.util.*

class Explore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val Club1 = arrayOf("Office of Student Involvement", "Dedicated to the Augustinian mission of the University, the Office of Student Involvement seeks to foster the growth of the total person, " +
                "one who builds community, values differences, leads ethically, and serves others. The office also provides a variety of services, programs, and activities to enhance campus life." +
                " Life at VU is filled with many opportunities for you to become an integral part of the community, " +
                "as well as enriching your time at the university. There are so many options to choose from that will add to your time on campus.", "studentinvolvement@villanova.edu")
        val Club2 = arrayOf("POWER", "As members of POWER, we work in teams to sponsor and participate in events related to health topics that are pertinent to our fellow Wildcats. We have the opportunity" +
                " to plan events and programs, create social media and marketing campaigns, present to student organizations, assist in larger Office of Health Promotion" +
                " events and more! We can also host interactive health programs for Residence Life, Student Organizations, Academic college, and more. " +
                "These programs are available by request, so be sure to reach out!", "samantha.tatulli@villanova.edu")
        val Club3 = arrayOf("Pride", "VU Pride is a group of students, faculty, and staff working together to help raise awareness and a better understanding about the LGBTQ+ Community on Villanova's campus." +
                " In particular, VU Pride seeks to foster understanding of sexual orientation and gender identity and provide a judgement-free environment to promote tolerance on Villanova University's campus."
            , "vupride@villanova.edu")
        val Club4 = arrayOf("Blue Key Society", "The Blue Key Society serves as tour guides to prospective students and help organize the open houses and Candidates' Days we host each year.  S" +
                "tudents in this club are passionate about Villanova and want to share their home with prospective students." +
                "Today, the club has over 250 active members and has helped give tours to over 56,000 visitors each year!", "exec@vubluekey.com")

        val queueSpot = intent.getStringExtra("queueSpot")
        val SearchResult = intent.getStringExtra("SearchResult")


        fun updateInfo(queue: Int): Unit {
            if (queue==1){
                Title.text=Club1[0]
                Information.text=Club1[1]
                Contact.text=Club1[2]
                ClubImage.setImageResource(R.drawable.villanovabb)
            }
            if (queue==2){
                Title.text=Club2[0]
                Information.text=Club2[1]
                Contact.text=Club2[2]
                ClubImage.setImageResource(R.drawable.powerbb)
            }
            if (queue==3){
                Title.text=Club3[0]
                Information.text=Club3[1]
                Contact.text=Club3[2]
                ClubImage.setImageResource(R.drawable.pride)
            }
            if (queue==4){
                Title.text=Club4[0]
                Information.text=Club4[1]
                Contact.text=Club4[2]
                ClubImage.setImageResource(R.drawable.bbluekey)
            }
            if (queue==5) {
                Title.text = "No More Clubs!"
                Information.text = ""
                Contact.text = ""
                StackInfo.text=""
                ClubImage.setImageResource(R.drawable.qricon)
                emailimage.setVisibility(View.INVISIBLE)
            }
        }

        //CREATION OF THE QUEUE FOR CLUB ROTATIONS
        var queue: Queue<Int> = ArrayDeque<Int>()
        if (queueSpot=="2"){
            queue.add(2)
        }
        if (SearchResult=="Pride"){
            queue.add(3)
        }
        else {
            queue.add(1)
            queue.add(2)
            queue.add(3)
            queue.add(4)
        }
        updateInfo(queue.peek())
        StackInfo.text=("")

        //Joins current club, if hooked up to a backend system, joining a club would alert the organization and then allow notifications to be sent to the device from the backend
        JoinButton.setOnClickListener {
            if(queue.peek()==null){
                StackInfo.text=("Check Back Soon To See If Any New Clubs Are Added")
            }
            else {
                queue.remove()
                if (queue.peek() == null) {
                    updateInfo(5)
                }
                else {
                    starsgif.setVisibility(View.VISIBLE)
                    StackInfo.text="***You Joined A Club!***"
                    Handler().postDelayed({
                        starsgif.setVisibility(View.INVISIBLE)
                        StackInfo.text=""
                        updateInfo(queue.peek())
                    }, 5000)
                }
            }
        }

        //REMOVES CURRENT CLUB
        HideButton.setOnClickListener {
            if(queue.peek()==null){
                StackInfo.text=("Check Back Soon To See If Any New Clubs Are Added")
            }
            else {
                queue.remove()
                if (queue.peek() == null) {
                    updateInfo(5)
                } else
                    updateInfo(queue.peek())
            }
        }

        //REMOVES CURRENT CLUB AND PUTS IT AT BOTTOM OF THE QUEUE
        MaybeButton.setOnClickListener {
            if(queue.peek()==null){
                StackInfo.text=("Check Back Soon To See If Any New Clubs Are Added")
            }
            else {
                val maybeTemp = queue.peek()
                queue.remove()
                queue.add(maybeTemp)
                if (queue.peek() == null) {
                    updateInfo(5)
                } else
                    updateInfo(queue.peek())
            }
        }


    }
}
