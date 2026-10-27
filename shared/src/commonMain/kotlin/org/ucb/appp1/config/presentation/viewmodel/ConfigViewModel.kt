package org.ucb.appp1.config.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.ucb.appp1.config.data.entity.ConfigItem
import org.ucb.appp1.config.data.repository.ConfigRepository

class ConfigViewModel(private val repository: ConfigRepository) : ViewModel() {

    val configs: StateFlow<List<ConfigItem>> = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedInitialDataIfNeeded()
        }
    }

    fun addConfig(clave: String, valor: String) {
        viewModelScope.launch {
            if (clave.isNotBlank() && valor.isNotBlank()) {
                repository.insert(
                    ConfigItem(
                        clave = clave.trim(),
                        valor = valor.trim(),
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun deleteConfig(id: Long) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }
}
