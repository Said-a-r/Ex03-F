package org.ucb.appp1.novedades.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ucb.appp1.novedades.data.entity.TablaNovedad
import org.ucb.appp1.novedades.data.repository.NovedadRepository
import org.ucb.appp1.scheduler.MyFirebaseMessagingService

class NovedadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val repository: NovedadRepository by inject()

    override suspend fun doWork(): androidx.work.ListenableWorker.Result {
        return try {
            val previousLatest = repository.getLatest()
            val fakeNews = listOf(
                TablaNovedad(
                    titulo = "Novedad #${System.currentTimeMillis()}",
                    descripcion = "Actualización periódica descargada con éxito.",
                    timestamp = System.currentTimeMillis()
                )
            )

            val newLatest = fakeNews.first()
            val hasNew = previousLatest == null || newLatest.timestamp > previousLatest.timestamp

            repository.insertAll(fakeNews)

            if (hasNew) {
                MyFirebaseMessagingService.showInternalNotification(
                    applicationContext,
                    newLatest.titulo,
                    newLatest.descripcion
                )
            }

            androidx.work.ListenableWorker.Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                androidx.work.ListenableWorker.Result.retry()
            } else {
                androidx.work.ListenableWorker.Result.failure()
            }
        }
    }
}
