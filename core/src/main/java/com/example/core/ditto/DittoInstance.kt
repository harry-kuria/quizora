package com.example.core.ditto

import android.content.Context
import live.ditto.Ditto
import live.ditto.DittoIdentity
import live.ditto.android.DefaultAndroidDittoDependencies
import live.ditto.transports.DittoTransportConfig

object DittoInstance {
    lateinit var ditto: Ditto

    fun initialize(context: Context, appName: String, sharedKey: String, deviceName: String) {
        val androidDependencies = DefaultAndroidDittoDependencies(context)
        val identity = DittoIdentity.OnlinePlayground(
            dependencies = androidDependencies,
            appId = appName,
            token = sharedKey,
            enableDittoCloudSync = false
        )

        ditto = Ditto(androidDependencies, identity)
        val transportConfig = DittoTransportConfig()
        transportConfig.enableAllPeerToPeer()
        ditto.transportConfig = transportConfig

        ditto.startSync()

    }
}