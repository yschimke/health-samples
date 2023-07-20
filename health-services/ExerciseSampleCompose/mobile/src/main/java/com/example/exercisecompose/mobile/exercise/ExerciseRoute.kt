package com.example.exercisecompose.mobile.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exercisecompose.exercise.proto.ExerciseProto.ExerciseStatus

@Composable
fun ExerciseRoute(
    viewModel: ExerciseViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsState()

    ExerciseScreen(uiState)
}

@Composable
private fun ExerciseScreen(uiState: ExerciseScreenState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.exerciseState != null) {
                Text(text = "Connected")
                Text(text = "Status: ${uiState.exerciseState.status}")
            } else {
                Text(text = "Not Connected")
            }
        }
    }
}

data class ExerciseScreenState(
    val exerciseState: ExerciseStatus?
)