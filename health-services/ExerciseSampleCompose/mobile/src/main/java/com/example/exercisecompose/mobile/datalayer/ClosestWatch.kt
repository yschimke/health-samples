@file:OptIn(ExperimentalHorologistApi::class)

package com.example.exercisecompose.mobile.datalayer

import com.google.android.gms.wearable.CapabilityClient
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.TargetNodeId
import com.google.android.horologist.data.WearDataLayerRegistry
import kotlinx.coroutines.tasks.await

object ClosestWatch : TargetNodeId {
    override suspend fun evaluate(dataLayerRegistry: WearDataLayerRegistry): String? {
        val capabilities = dataLayerRegistry.capabilityClient.getCapability(
            "exercise_watch",
            CapabilityClient.FILTER_REACHABLE
        ).await()

        return capabilities.nodes.firstOrNull()?.id
    }
}