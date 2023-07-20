package com.example.exercisecompose.common

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.exercisecompose.exercise.proto.ExerciseProto.ExerciseStatus
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ExerciseStatusSerializer : Serializer<ExerciseStatus> {
    override val defaultValue: ExerciseStatus = ExerciseStatus.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ExerciseStatus {
        try {
            return ExerciseStatus.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ExerciseStatus, output: OutputStream) = t.writeTo(output)
}
