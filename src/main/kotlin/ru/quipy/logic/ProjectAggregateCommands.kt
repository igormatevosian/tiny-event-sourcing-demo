package ru.quipy.logic

import ru.quipy.api.*
import java.util.*


// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(id: UUID, title: String, creatorId: String): ProjectCreatedEvent {
    return ProjectCreatedEvent(
        projectId = id,
        title = title,
        creatorId = creatorId,
    )
}

fun ProjectAggregateState.addUser(userId: UUID): UserAddedToProjectEvent {
    return UserAddedToProjectEvent(userId = userId, projectId = this.getId())
}

fun ProjectAggregateState.addTask(name: String): TaskCreatedEvent {
    return TaskCreatedEvent(projectId = this.getId(), taskId = UUID.randomUUID(), taskName = name)
}

fun ProjectAggregateState.rename(newName: String): ProjectRenamedEvent {
    return ProjectRenamedEvent(projectId = this.getId(), newName = newName)
}

fun ProjectAggregateState.removeUser(userId: UUID): UserRemovedFromProjectEvent {
    return UserRemovedFromProjectEvent(userId = userId)
}

fun ProjectAggregateState.createTaskStatus(name: String, color: String, columnNumber: Int): TaskStatusCreatedEvent {
    if (taskStatuses.values.any { it.name == name }) {
        throw IllegalArgumentException("Task status already exists: $name")
    }
    return TaskStatusCreatedEvent(
        projectId = this.getId(),
        statusId = UUID.randomUUID(),
        statusName = name,
        color = color,
        columnNumber = columnNumber
    )
}

fun ProjectAggregateState.deleteTaskStatus(statusId: UUID): TaskStatusDeletedEvent {
    return TaskStatusDeletedEvent(statusId = statusId)
}

fun ProjectAggregateState.changeTaskStatusColumnNumber(
    taskStatusId: UUID,
    newColumnNumber: Int
): TaskStatusColumnNumberChangedEvent {
    if (taskStatuses.values.any { it.number == newColumnNumber }) {
        throw IllegalArgumentException("Task status with this column number already exists: $newColumnNumber")
    }
    return TaskStatusColumnNumberChangedEvent(statusId = taskStatusId, newColumnNumber = newColumnNumber)
}

