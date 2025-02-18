package com.indusjs.cm.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 *
 */
abstract class BaseUseCaseFlow<IN, OUT>(private val dispatcher: CoroutineDispatcher) {

    abstract suspend fun block(param: IN): Flow<OUT>

    suspend operator fun invoke(param: IN): Flow<Result<OUT>> = block(param)
        .flowOn(dispatcher)
        .map { Result.success(it) }
        .catch { emit(Result.failure(it)) }
}