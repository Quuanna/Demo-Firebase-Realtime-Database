package com.example.demo_firebase_realtime_database

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var myRef: DatabaseReference
    private lateinit var myRef2: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btnWrite.setOnClickListener(this)
    }


    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btnWrite -> {
                setWrite()
                getReadData()
            }
        }
    }

    private fun setWrite() {
        myRef = mDatabase.getReference("message")
        myRef2 = mDatabase.getReference("message2")
        myRef.setValue(editText.text.toString())
        myRef2.setValue(editText.text.toString())
    }

    private fun getReadData() {
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value."+  error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                tvData.text = value
            }

        })
    }

}