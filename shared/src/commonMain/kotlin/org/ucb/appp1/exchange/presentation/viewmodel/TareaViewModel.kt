package org.ucb.appp1.exchange.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.ucb.appp1.exchange.data.datasource.FirestoreDataSource
import org.ucb.appp1.exchange.data.entity.Tarea

class TareaViewModel(
    private val firestoreDataSource: FirestoreDataSource
) : ViewModel() {

    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    private val _nuevaTareaEvento = MutableStateFlow<String?>(null)
    val nuevaTareaEvento: StateFlow<String?> = _nuevaTareaEvento

    private var isFirstLoad = true

    init {
        viewModelScope.launch {
            firestoreDataSource.getTareasFlow().collectLatest { listaNueva ->
                if (!isFirstLoad && listaNueva.size > _tareas.value.size) {
                    val tareaMasReciente = listaNueva.lastOrNull()
                    if (tareaMasReciente != null) {
                        _nuevaTareaEvento.value = "Nueva tarea: ${tareaMasReciente.titulo}"
                    }
                }
                _tareas.value = listaNueva
                isFirstLoad = false
            }
        }
    }

    fun limpiarEventoNotificacion() {
        _nuevaTareaEvento.value = null
    }

    fun agregarTarea(titulo: String) {
        viewModelScope.launch {
            val nuevaTarea = Tarea(
                titulo = titulo,
                timestamp = kotlin.random.Random.nextLong()
            )
            firestoreDataSource.addTarea(nuevaTarea)
        }
    }
}