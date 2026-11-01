package org.ucb.appp1.exchange.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ucb.appp1.exchange.data.entity.DollarEntity
import org.ucb.appp1.exchange.data.entity.Tarea

class FirestoreDataSource {

    suspend fun saveDollarToCloud(dollar: DollarEntity) {
        val firestore = Firebase.firestore
        firestore.collection("dollars").add(dollar)
    }

    suspend fun getDollarsFromCloud(): List<DollarEntity> {
        val firestore = Firebase.firestore
        val snapshot = firestore.collection("dollars").get()

        return snapshot.documents.map { it.data<DollarEntity>() }
    }

    suspend fun addTarea(tarea: Tarea) {
        val firestore = Firebase.firestore
        firestore.collection("tareas").add(tarea)
    }

    // ✅ Aquí usamos el Listener en tiempo real
    fun getTareasFlow(): Flow<List<Tarea>> {
        val firestore = Firebase.firestore
        return firestore.collection("tareas").snapshots.map { snapshot ->
            snapshot.documents.map { doc ->
                doc.data<Tarea>()
            }
        }
    }
}
