package com.example.exercisesamplecompose.remote

import com.example.exercisecompose.exercise.proto.ExerciseProto
import com.example.exercisecompose.exercise.proto.ExerciseServiceGrpcKt
import com.example.exercisecompose.exercise.proto.exerciseStatus
import com.example.exercisesamplecompose.service.ExerciseServiceMonitor
import kotlinx.coroutines.flow.first
import kotlin.coroutines.EmptyCoroutineContext


class ExerciseServiceImpl(val monitor: ExerciseServiceMonitor): ExerciseServiceGrpcKt.ExerciseServiceCoroutineImplBase(EmptyCoroutineContext) {
    override suspend fun control(request: ExerciseProto.ExerciseAction): ExerciseProto.ExerciseStatus {
        when (request.action) {
            ExerciseProto.Action.PAUSE -> {
            }
            ExerciseProto.Action.RESUME -> {
            }
            ExerciseProto.Action.START -> {
            }
            ExerciseProto.Action.STOP -> {
            }
            else -> {}
        }
        return readStatus()
    }

    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    private suspend fun readStatus(): ExerciseProto.ExerciseStatus {
        val state = monitor.exerciseServiceState.first()

        return exerciseStatus {
            this.status = state.exerciseState?.toProto()
        }
    }

    override suspend fun status(request: ExerciseProto.ExerciseAction): ExerciseProto.ExerciseStatus {
        return readStatus()
    }
}