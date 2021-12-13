package com.example.td5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var contacts: ArrayList<Contact> = ArrayList<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rv:RecyclerView = findViewById<RecyclerView>(R.id.rvContacts)

        contacts.add(Contact("BEN SALEM", "Zakaria", "https://pbs.twimg.com/profile_images/492123209504784388/U0zn1OSb_400x400.jpeg"))
        contacts.add(Contact("BEN SALEM", "Maroine","https://img.a.transfermarkt.technology/portrait/big/19085-1476194714.jpg?lm=1"))
        contacts.add(Contact("KORIS", "Abdellah","https://media-exp1.licdn.com/dms/image/C4D03AQEhR0F0coq0QA/profile-displayphoto-shrink_200_200/0/1618001218426?e=1643846400&v=beta&t=gjk1wmJbWCAuBGk8JqV3cC0Gz0o34cALEuvxDzTrA8Y"))
        contacts.add(Contact("ENNOURI", "Hind","https://media-exp1.licdn.com/dms/image/C4E03AQHcLWAOFknYpw/profile-displayphoto-shrink_100_100/0/1636048518047?e=1642636800&v=beta&t=7PwUwUNTGcn-XBXGQnAZhrwCa6YMBd7Pt-oXUspTpHU"))


        var ca:ContactAdapter = ContactAdapter(contacts)

        rv.adapter = ca
        rv.layoutManager = LinearLayoutManager(this)
    }
}