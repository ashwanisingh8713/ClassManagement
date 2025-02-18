package com.indusjs.cm.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Base class for use cases
 */
abstract class BaseUseCase<IN, OUT>(private val dispatcher: CoroutineDispatcher) {

    abstract suspend fun block(param: IN): OUT

    suspend operator fun invoke(param: IN): Result<OUT> = withContext(dispatcher) {
        try {
            Result.success(block(param))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}