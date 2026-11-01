package org.ucb.appp1.novedades.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.ucb.appp1.novedades.data.entity.TablaNovedad
import org.ucb.appp1.novedades.data.repository.NovedadRepository

class NovedadViewModel(private val repository: NovedadRepository) : ViewModel() {

    val novedades: StateFlow<List<TablaNovedad>> = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun addManualNovedad(titulo: String, descripcion: String) {
        viewModelScope.launch {
            if (titulo.isNotBlank() && descripcion.isNotBlank()) {
                repository.insert(
                    TablaNovedad(
                        titulo = titulo.trim(),
                        descripcion = descripcion.trim(),
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }
}
