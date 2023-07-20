package com.example.exercisecompose.common.util

import com.google.protobuf.Timestamp

fun Long.toProtoTimestamp(): Timestamp {
    return Timestamp.newBuilder()
        .setSeconds(this / 1000)
        .setNanos((this % 1000).toInt() * 1000000)
        .build()
}