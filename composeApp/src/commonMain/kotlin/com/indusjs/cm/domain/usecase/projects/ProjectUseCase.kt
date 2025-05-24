package com.indusjs.cm.domain.usecase.projects

import com.indusjs.cm.domain.model.project.Projects
import com.indusjs.cm.domain.repo.IProjectRepo
import com.indusjs.cm.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class ProjectUseCase(private val repository: IProjectRepo, dispatcher: CoroutineDispatcher) : BaseUseCase<Any?, Projects>(dispatcher) {

    override suspend fun block(param: Any?): Projects {
        return repository.getProjects(param)
    }
}