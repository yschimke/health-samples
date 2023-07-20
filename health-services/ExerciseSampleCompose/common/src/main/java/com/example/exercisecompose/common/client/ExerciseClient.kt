@file:OptIn(ExperimentalHorologistApi::class, ExperimentalHorologistApi::class)

package com.example.exercisecompose.common.client

import com.example.exercisecompose.exercise.proto.ExerciseProto
import com.example.exercisecompose.exercise.proto.ExerciseProto.Action
import com.example.exercisecompose.exercise.proto.ExerciseServiceGrpcKt
import com.example.exercisecompose.exercise.proto.exerciseAction
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.ProtoDataStoreHelper.protoFlow
import com.google.android.horologist.data.TargetNodeId
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.android.horologist.datalayer.grpc.GrpcExtensions.grpcClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class ExerciseClient(
    registry: WearDataLayerRegistry,
    coroutineScope: CoroutineScope,
    targetNodeId: TargetNodeId
) {
    val client = registry.grpcClient(
        targetNodeId,
        ExercisePath,
        coroutineScope,
        ExerciseServiceGrpcKt::ExerciseServiceCoroutineStub
    )

    val exerciseFlow: Flow<ExerciseProto.ExerciseStatus> = registry.protoFlow<ExerciseProto.ExerciseStatus>(targetNodeId)
    val updatesFlow = MutableStateFlow<ExerciseProto.ExerciseStatus?>(null)

    val exerciseState = combine(exerciseFlow, updatesFlow) { data, update ->
        if (update != null && update.timestamp.seconds > data.timestamp.seconds) {
            update
        } else {
            data
        }
    }

    suspend fun play() {
        controlAction(Action.RESUME)
    }

    suspend fun pause() {
        controlAction(Action.PAUSE)
    }

    private suspend fun controlAction(action: Action) {
        val exerciseStatus = client.control(exerciseAction { this.action = action })
        updatesFlow.value = exerciseStatus
    }

    companion object {
        val ExercisePath = "/grpc/exercise"
    }
}