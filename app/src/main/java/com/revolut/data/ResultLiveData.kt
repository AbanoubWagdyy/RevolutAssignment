package com.revolut.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        while (true) {
            emit(Result.loading<T>())
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Result.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
                val source = databaseQuery.invoke().map { Result.success(it) }
                emitSource(source)
            } else if (responseStatus.status == Result.Status.ERROR) {
                emit(Result.error<T>(responseStatus.message!!))
            }
            delay(8000)
        }
    }