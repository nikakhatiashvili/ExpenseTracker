package com.example.expensetracker.data.tribe

import com.example.expensetracker.R
import com.example.expensetracker.common.ResourceManager
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.tribe.Tribe
import com.example.expensetracker.domain.tribe.TribeRepository
import com.example.expensetracker.domain.tribe.UserTribe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TribeRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth,
    private val tribeDataStore: TribeDataStore,
    private val resourceManager: ResourceManager
) : TribeRepository {
    override suspend fun createTribe(name: String) {
        val ref = firebaseDatabase.getReference(resourceManager.provide(R.string.user))
            .child(firebaseAuth.uid.toString())
        val databaseRef = firebaseDatabase.getReference(resourceManager.provide(R.string.tasks))
        val id = java.util.UUID.randomUUID().toString()
        databaseRef.child(id).setValue(Tribe(name))
        ref.setValue(UserTribe(true, id))
        CoroutineScope(Dispatchers.IO).launch {
            tribeDataStore.saveTribeId(id)
        }
    }
}
