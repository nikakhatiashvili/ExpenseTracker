package com.example.expensetracker.data.manage_tribe

import com.example.expensetracker.R
import com.example.expensetracker.common.ResourceManager
import com.example.expensetracker.domain.manage_tribe.ManageTribeRepository
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class ManageTribeRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val tribeDataStore: TribeDataStore,
    private val resourceManager: ResourceManager
) : ManageTribeRepository {

    override suspend fun addTask(task: Task) {
        val ref = firebaseDatabase.getReference(resourceManager.provide(R.string.tribes))
            .child(tribeDataStore.getTribeId())
            .child(resourceManager.provide(R.string.tasks))
        ref.push().setValue(task)
    }
}
