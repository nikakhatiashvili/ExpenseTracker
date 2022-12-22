package com.example.expensetracker.data.tribe

import android.widget.Toast
import com.example.expensetracker.R
import com.example.expensetracker.common.ResourceManager
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.tribe.Tribe
import com.example.expensetracker.domain.tribe.TribeRepository
import com.example.expensetracker.domain.tribe.UserTribe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class TribeRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth,
    private val tribeDataStore: TribeDataStore,
    private val resourceManager: ResourceManager
) : TribeRepository {

    override suspend fun createTribe(name: String) {
        val databaseRef = firebaseDatabase.getReference(resourceManager.provide(R.string.tribes))
        val id = java.util.UUID.randomUUID().toString()
        databaseRef.child(id).setValue(Tribe(name, id))
        saveTribeId(true,id)
    }

    override suspend fun joinTribe(inviteId: String) {
        val ref = firebaseDatabase.getReference(resourceManager.provide(R.string.tribes))
        println("before orderByChild")
        ref.child(inviteId).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    saveTribeId(true,inviteId)
                }else {
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(false)
            }
        })
    }

    private fun saveTribeId(isInTribe: Boolean,tribeId:String){
        val userRef = firebaseDatabase.getReference(resourceManager.provide(R.string.user))
            .child(firebaseAuth.uid.toString())
        userRef.setValue(UserTribe(isInTribe,tribeId))
        CoroutineScope(Dispatchers.IO).launch {
            tribeDataStore.saveTribeId(tribeId)
        }
    }
}


// 4ab98153-9fd3-4a14-aabd-9eba6c05178e