package com.example.gomovie

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_reserve_ticket.*
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class ReserveTicket : AppCompatActivity() {

    var email: String = ""
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_ticket)
        email = getIntent().getStringExtra("Email")
        appExecutors = AppExecutors()

        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val movieSpinner = findViewById<Spinner>(R.id.movieSpinner)
        val cinemaSpinner = findViewById<Spinner>(R.id.cinemaSpinner)
        val timeSpinner = findViewById<Spinner>(R.id.timeSpinner)
        val mPickDateBtn = findViewById<Button>(R.id.pickDateBtn)
        val textViewDate = findViewById<TextView>(R.id.date)
        val addSeats = findViewById<Button>(R.id.addSeat)
        val removeSeats = findViewById<Button>(R.id.removeSeat)
        val seats = findViewById<TextView>(R.id.seatNo)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val cinemaName = getIntent().getStringExtra("Cinema")
        val movieName = getIntent().getStringExtra("Movie")

        seats.setText("1")

        mPickDateBtn.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    textViewDate.setText("" + dayOfMonth + " " + monthOfYear + ", " + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        var timeList = ArrayList<String>()
        timeList.add("9AM")
        timeList.add("12PM")
        timeList.add("3PM")
        timeList.add("6PM")
        timeList.add("9PM")

        val timeAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeList)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter

        timeSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val textView = parent.getChildAt(0) as TextView
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                textView.setTypeface(Typeface.DEFAULT_BOLD)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // handle if you'd like to
            }
        })

        var movieList = ArrayList<String>()
        movieList.add("Joker")
        movieList.add("Maleficient: Mistress of Evil")
        movieList.add("Terminator: Dark Fate")
        movieList.add("The Addams Family")

        val movieAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movieList)
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        movieSpinner.adapter = movieAdapter

        movieSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val textView = parent.getChildAt(0) as TextView
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                textView.setTypeface(Typeface.DEFAULT_BOLD)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // handle if you'd like to
            }
        })

        addSeats.setOnClickListener {
            var seatsNo = Integer.parseInt(seats.text.toString())
            seatsNo = seatsNo.inc()
            seats.setText(seatsNo.toString())
        }

        removeSeats.setOnClickListener {
            var seatsNo = Integer.parseInt(seats.text.toString())

            if (seatsNo >= 2) {
                seatsNo = seatsNo.dec()
                seats.setText(seatsNo.toString())
            }
        }




        var cinemaList = ArrayList<String>()
        cinemaList.add("Shaw")
        cinemaList.add("Cathay")
        cinemaList.add("Golden Village")

        val cinemaAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cinemaList)
        cinemaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cinemaSpinner.adapter = cinemaAdapter

        cinemaSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val textView = parent.getChildAt(0) as TextView
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                textView.setTypeface(Typeface.DEFAULT_BOLD)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // handle if you'd like to
            }
        })

        if (!cinemaName.isNullOrEmpty()) {
            for (i in 0..(cinemaSpinner.adapter.count - 1)) {
                if (cinemaSpinner.adapter.getItem(i).toString() == cinemaName.toString()) {
                    cinemaSpinner.setSelection(i)
                    continue
                }
            }
        }

        if (!movieName.isNullOrEmpty()) {
            for (i in 0..(movieSpinner.adapter.count - 1)) {
                if (movieSpinner.adapter.getItem(i).toString() == movieName.toString()) {
                    movieSpinner.setSelection(i)
                    continue
                }
            }
        }

        confirmReserve.setOnClickListener {
            Log.d("EMAIL", "???")
            sendEmail()
            Toast.makeText(this, "Reservation Confirmed! Email Sent!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun sendEmail(){
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.port", "465")
            //props.put("mail.smtp.starttls.enable","true");

            props.put("mail.smtp.auth", "true");

            val session =  Session.getInstance(props,
                object : javax.mail.Authenticator() {
                    //Authenticating the password
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(Credentials.EMAIL, Credentials.PASSWORD)
                    }
                })

            try {
                //Creating MimeMessage object
                val mm = MimeMessage(session)
                val emailId = email
                //Setting sender address
                mm.setFrom(InternetAddress(session.getProperty("mail.from"), session.getProperty("mail.from.alias"), "UTF8"))
                //Adding receiver
                mm.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(emailId))
                //Adding subject
                mm.subject = "Ticket confirmation for " + movieSpinner.selectedItem.toString()
                //Adding message
                mm.setText("Your movie ticket for " + movieSpinner.selectedItem.toString() + " at " + cinemaSpinner.selectedItem.toString() +  " has been confirmed!\n" +
                        "Movie Date: " + findViewById<TextView>(R.id.date).text.toString() + "\nMovie Time: " +  timeSpinner.selectedItem.toString() +"\nNumber of tickets: " + findViewById<TextView>(R.id.seatNo).text.toString() )

                //Sending email
                Transport.send(mm)

                appExecutors.mainThread().execute {
                    //Something that should be executed on main thread.
                }

            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }
}