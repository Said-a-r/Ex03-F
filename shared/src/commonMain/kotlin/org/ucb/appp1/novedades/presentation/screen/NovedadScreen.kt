package org.ucb.appp1.novedades.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.novedades.presentation.viewmodel.NovedadViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovedadScreen(viewModel: NovedadViewModel = koinViewModel()) {
    val novedades by viewModel.novedades.collectAsState()

    var tituloInput by remember { mutableStateOf("") }
    var descInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Novedades") },
                actions = {
                    if (novedades.isNotEmpty()) {
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
                value = tituloInput,
                onValueChange = { tituloInput = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = descInput,
                onValueChange = { descInput = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.addManualNovedad(tituloInput, descInput)
                    tituloInput = ""
                    descInput = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Novedad Manual")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Historial (Flow):", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(novedades, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = item.titulo, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.descripcion, style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Timestamp: ${item.timestamp}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
