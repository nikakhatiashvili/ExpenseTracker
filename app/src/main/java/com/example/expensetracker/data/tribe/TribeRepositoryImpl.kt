package com.example.expensetracker.data.tribe

import com.example.expensetracker.domain.tribe.Tribe
import com.example.expensetracker.domain.tribe.TribeRepository
import com.example.expensetracker.domain.tribe.UserTribe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class TribeRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
):TribeRepository {
    override suspend fun createTribe(name: String) {
        val ref = firebaseDatabase.getReference("user").child(firebaseAuth.uid.toString())

        ref.child("inTribe").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val isInTribe = dataSnapshot.getValue(Boolean::class.java)
                if (isInTribe == false){
                    println(isInTribe.toString().plus("2"))
                    val databaseRef = firebaseDatabase.getReference("tribes")
                    val d = java.util.UUID.randomUUID().toString()
                    databaseRef.child(d).setValue(Tribe(name))
                    ref.setValue(UserTribe(true,d))
                }
                println(isInTribe.toString().plus("1"))
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
