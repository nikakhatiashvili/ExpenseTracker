package com.example.expensetracker.data.tasks

import android.util.Log.d
import com.example.expensetracker.domain.tasks.Task
import com.example.expensetracker.domain.tasks.TasksRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val databaseReference: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
) : TasksRepository {
    override suspend fun getTasks(): Flow<MutableList<Task>> = flow {
        val ref = databaseReference.getReference("tribes").child("dc23fffb-21d8-487b-9001-66d8092d13ad")

        //val queryList = mutableListOf<Task>()
        val query = mutableListOf<String>()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val item = i.getValue()
                        query.add(item as String)
                    }
                    d("sadagas",query.toString())
//                    emit(queryList)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
