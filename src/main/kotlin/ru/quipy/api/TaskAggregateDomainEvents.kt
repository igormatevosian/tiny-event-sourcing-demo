package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val ASSIGNEE_ADDED_TO_TASK_EVENT = "ASSIGNEE_ADDED_TO_TASK_EVENT"
const val ASSIGNEE_REMOVED_FROM_TASK_EVENT = "ASSIGNEE_REMOVED_FROM_TASK_EVENT"
const val TASK_RENAMED_EVENT = "TASK_RENAMED_EVENT"
const val TASK_CLOSED_EVENT = "TASK_CLOSED_EVENT"
const val TASK_REOPENED_EVENT = "TASK_REOPENED_EVENT"
const val TASK_STATUS_CHANGED_EVENT = "TASK_STATUS_CHANGED_EVENT"

// API
@DomainEvent(name = ASSIGNEE_ADDED_TO_TASK_EVENT)
class AssigneeAddedToTaskEvent(
    val nickname: String,
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis()
) : Event<TaskAggregate>(
    name = ASSIGNEE_ADDED_TO_TASK_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = ASSIGNEE_REMOVED_FROM_TASK_EVENT)
class AssigneeRemovedFromTaskEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis()
) : Event<TaskAggregate>(
    name = ASSIGNEE_REMOVED_FROM_TASK_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_RENAMED_EVENT)
class TaskRenamedEvent(
    val taskId: UUID,
    val newName: String,
    createdAt: Long = System.currentTimeMillis()
) : Event<TaskAggregate>(
    name = TASK_RENAMED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_CLOSED_EVENT)
class TaskClosedEvent(
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis()
) : Event<TaskAggregate>(
    name = TASK_CLOSED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_REOPENED_EVENT)
class TaskReopenedEvent(
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis()
) : Event<TaskAggregate>(
    name = TASK_REOPENED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_STATUS_CHANGED_EVENT)
class TaskStatusChangedEvent(
    val taskId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis()
) : Event<TaskAggregate>(
    name = TASK_STATUS_CHANGED_EVENT,
    createdAt = createdAt
)