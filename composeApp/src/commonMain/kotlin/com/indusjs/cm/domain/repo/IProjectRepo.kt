package com.indusjs.cm.domain.repo

import com.indusjs.cm.domain.model.project.ProjectItem
import com.indusjs.cm.domain.model.project.Projects

interface IProjectRepo {
    suspend fun getProjects(params: Any?): Projects
}