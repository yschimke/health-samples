@file:OptIn(ExperimentalHorologistApi::class)

package com.example.exercisecompose.mobile.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercisecompose.common.client.ExerciseClient
import com.example.exercisecompose.common.util.exerciseDataLayerRegistry
import com.example.exercisecompose.mobile.datalayer.ClosestWatch
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    val registry = application.exerciseDataLayerRegistry(viewModelScope)

    val client = ExerciseClient(registry, viewModelScope, ClosestWatch)

    val state = client.exerciseState.map {
        ExerciseScreenState(exerciseState = it)
    }.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ExerciseScreenState(null)
    )
}