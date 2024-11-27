package ru.quipy.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projections.ProjectProjection
import ru.quipy.projections.TaskProjection
import ru.quipy.projections.UserProjection
import java.util.*

interface ProjectProjectionRepository : MongoRepository<ProjectProjection, UUID> {
    fun findByProjectId(projectId: UUID): List<ProjectProjection>
}

interface TaskProjectionRepository : MongoRepository<TaskProjection, UUID>

interface UserProjectionRepository : MongoRepository<UserProjection, UUID> {
    fun findByName(name: String): UserProjection?
}