@file:OptIn(ExperimentalHorologistApi::class)

package com.example.exercisecompose.common.util

import android.app.Application
import com.example.exercisecompose.common.ExerciseStatusSerializer
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import kotlinx.coroutines.CoroutineScope

fun Application.exerciseDataLayerRegistry(scope: CoroutineScope): WearDataLayerRegistry {
    return WearDataLayerRegistry.fromContext(this, scope).apply {
        registerSerializer(ExerciseStatusSerializer)
    }
}