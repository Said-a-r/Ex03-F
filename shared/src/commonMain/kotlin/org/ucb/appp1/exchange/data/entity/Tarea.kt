package org.ucb.appp1.exchange.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tarea(
    val id: String = "",
    val titulo: String = "",
    val timestamp: Long = 0
)