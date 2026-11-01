package org.ucb.appp1.config.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.config.presentation.viewmodel.ConfigViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(viewModel: ConfigViewModel = koinViewModel()) {
    val configs by viewModel.configs.collectAsState()

    var claveInput by remember { mutableStateOf("") }
    var valorInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración Local (Room)") },
                actions = {
                    if (configs.isNotEmpty()) {
                        TextButton(onClick = { viewModel.deleteAll() }) {
                            Text("Borrar Todo")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = claveInput,
                onValueChange = { claveInput = it },
                label = { Text("Clave") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = valorInput,
                onValueChange = { valorInput = it },
                label = { Text("Valor") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.addConfig(claveInput, valorInput)
                    claveInput = ""
                    valorInput = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Configuración")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Lista de Configuraciones:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(configs, key = { it.id }) { config ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Clave: ${config.clave}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Valor: ${config.valor}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Timestamp: ${config.timestamp}", style = MaterialTheme.typography.bodySmall)
                            }
                            TextButton(onClick = { viewModel.deleteConfig(config.id) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}
