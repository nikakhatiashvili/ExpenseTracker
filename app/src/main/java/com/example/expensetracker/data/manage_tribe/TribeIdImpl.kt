package com.example.expensetracker.data.manage_tribe

import com.example.expensetracker.R
import com.example.expensetracker.common.ResourceManager
import com.example.expensetracker.common.Result
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.manage_tribe.TribeIdRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TribeIdImpl @Inject constructor(
    private val tribeDataStore: TribeDataStore,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val resourceManager: ResourceManager
) : TribeIdRepository {
    override suspend fun saveTribeId(): Flow<Result<Boolean>> = callbackFlow {
        val ref = firebaseDatabase.getReference(resourceManager.provide(R.string.user))
            .child(firebaseAuth.currentUser?.uid!!)
        ref.child(resourceManager.provide(R.string.tribeId))
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val tribeId = dataSnapshot.getValue(String::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        if (!tribeId.isNullOrEmpty()) tribeDataStore.saveTribeId(tribeId)
                    }
                    trySend(Result.ApiSuccess(true))
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
        awaitClose {  }
    }
}
