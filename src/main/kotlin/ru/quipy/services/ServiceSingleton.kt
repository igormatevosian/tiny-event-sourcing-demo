package ru.quipy.services

import org.springframework.stereotype.Service
import ru.quipy.projections.UserProjection
import ru.quipy.repositories.ProjectProjectionRepository
import ru.quipy.repositories.TaskProjectionRepository
import ru.quipy.repositories.UserProjectionRepository

@Service
class ServiceSingleton(
    private val userProjectionRepository: UserProjectionRepository,
    private val projectProjectionRepository: ProjectProjectionRepository,
    private val taskProjectionRepository: TaskProjectionRepository
) {


    fun getUserByName(name: String): UserProjection? {
        return userProjectionRepository.findByName(name)
    }
}