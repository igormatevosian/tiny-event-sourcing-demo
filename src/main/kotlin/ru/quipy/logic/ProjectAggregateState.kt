package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class ProjectAggregateState : AggregateState<UUID, ProjectAggregate> {
    private lateinit var projectId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var projectTitle: String
    lateinit var creatorId: String
    var users = mutableListOf<UUID>()
    var tasks = mutableMapOf<UUID, TaskEntity>()
    var taskStatuses = mutableMapOf<UUID, TaskStatusEntity>()

    override fun getId() = projectId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun projectCreatedApply(event: ProjectCreatedEvent) {
        projectId = event.projectId
        projectTitle = event.title
        creatorId = event.creatorId
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun projectRenamedApply(event: ProjectRenamedEvent) {
        projectTitle = event.newName
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskStatusCreatedApply(event: TaskStatusCreatedEvent) {
        taskStatuses[event.statusId] = TaskStatusEntity(event.statusId, event.statusName, event.columnNumber)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskStatusDeletedApply(event: TaskStatusDeletedEvent) {
        taskStatuses.remove(event.statusId)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskStatusColumnNumberChangedApply(event: TaskStatusColumnNumberChangedEvent) {
        taskStatuses[event.statusId]?.number ?: event.newColumnNumber
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskCreatedApply(event: TaskCreatedEvent) {
        tasks[event.taskId] = TaskEntity(
            id = event.taskId,
            name = event.taskName,
            project = event.projectId,
            taskStatuses = mutableSetOf()
        )
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun addUserApply(event: UserAddedToProjectEvent) {
        users.add(event.userId)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun removeUserApply(event: UserRemovedFromProjectEvent) {
        users.remove(event.userId)
        updatedAt = createdAt
    }
}


data class TaskEntity(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String = "",
    val isOpened: Boolean = true,
    val project: UUID,
    val status: UUID? = null,
    val taskStatuses: MutableSet<UUID>
)

data class TaskStatusEntity(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    var number: Int
)
