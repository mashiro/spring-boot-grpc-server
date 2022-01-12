package com.example.grpcserver

import com.example.grpc.HelloRequest
import com.example.grpc.HelloResponse
import com.example.grpc.HelloServiceGrpcKt
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class HelloService : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest): HelloResponse {
        val name = if (request.hasNickname()) request.nickname else request.name

        return HelloResponse.newBuilder().apply {
            message = "Hello $name"
            if (request.hasNickname()) {
                nickname = request.nickname
            }
        }.build()
    }
}
