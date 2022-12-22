package com.example.expensetracker.data.tasks

import com.example.expensetracker.R
import com.example.expensetracker.common.ResourceManager
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.tasks.Task
import com.example.expensetracker.domain.tasks.TasksRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val databaseReference: FirebaseDatabase,
    private val tribeDataStore: TribeDataStore,
    private val resourceManager: ResourceManager
) : TasksRepository {
    override suspend fun getTasks(): Flow<MutableList<Task>> = callbackFlow {
        println(tribeDataStore.getTribeId().plus("tribe id first time"))
        val ref = databaseReference.getReference(resourceManager.provide(R.string.tribes))
            .child(tribeDataStore.getTribeId()).child(resourceManager.provide(R.string.tasks))
        val queryList = mutableListOf<Task>()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val item = i.getValue(Task::class.java)
                        queryList.add(item!!)
                    }
                    trySend(queryList)
                }else{
                    println("entered else")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        awaitClose {}
    }

}
