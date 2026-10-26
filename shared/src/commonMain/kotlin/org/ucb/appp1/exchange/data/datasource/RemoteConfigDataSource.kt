package org.ucb.appp1.exchange.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig

class RemoteConfigDataSource {
    suspend fun getDollarPrice(): Double {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate()

        val priceDouble = remoteConfig.getValue("dollar_price").asDouble()

        return priceDouble
    }
}