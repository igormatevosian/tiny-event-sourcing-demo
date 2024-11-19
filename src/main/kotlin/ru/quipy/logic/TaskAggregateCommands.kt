package ru.quipy.logic

import ru.quipy.api.*
import java.util.UUID

fun TaskAggregateState.addUser(userId : UUID): AssigneeAddedToTaskEvent {
    return AssigneeAddedToTaskEvent(
        userId = userId,
        taskId = this.getId(),
    )
}
fun TaskAggregateState.removeUser(userId : UUID): AssigneeRemovedFromTaskEvent {
    return AssigneeRemovedFromTaskEvent(
        taskId = getId(),
        userId = userId,
    )
}
fun TaskAggregateState.rename(newName : String): TaskRenamedEvent {
    return TaskRenamedEvent(
        taskId = getId(),
        newName = newName,
    )
}
fun TaskAggregateState.close(): TaskClosedEvent {
    return TaskClosedEvent(
        taskId = getId(),
    )
}
fun TaskAggregateState.reopen(): TaskReopenedEvent {
    return TaskReopenedEvent(
        taskId = getId(),
    )
}
fun TaskAggregateState.changeTaskStatus(id: UUID): TaskStatusChangedEvent {
    return TaskStatusChangedEvent(
        taskId = getId(),
        statusId = id,
    )
}