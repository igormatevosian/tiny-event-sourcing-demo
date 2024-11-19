package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()
    lateinit var description: String
    lateinit var projectId: UUID
    lateinit var statusId: UUID
    var isOpen : Boolean = true
    lateinit var name: String
    var users = mutableListOf<UUID>()

    override fun getId() = taskId
    @StateTransitionFunc
    fun AssigneeAddedToTaskApply(event: AssigneeAddedToTaskEvent) {
        taskId = event.taskId
        users.add(event.userId)
        updatedAt = createdAt
    }
    @StateTransitionFunc
    fun AssigneeRemovedFromTaskApply(event: AssigneeRemovedFromTaskEvent) {
        users.remove(event.userId)
        updatedAt = createdAt
    }
    @StateTransitionFunc
    fun TaskRenamedApply(event: TaskRenamedEvent) {
        name = event.newName
        updatedAt = createdAt
    }
    @StateTransitionFunc
    fun TaskClosedkApply(event: TaskClosedEvent) {
        isOpen = false
        updatedAt = createdAt
    }
    @StateTransitionFunc
    fun TaskReopenedApply(event: TaskReopenedEvent) {
        isOpen = true
        updatedAt = createdAt
    }
    @StateTransitionFunc
    fun TaskStatusChangedApply(event: TaskStatusChangedEvent) {
        isOpen = false
        updatedAt = createdAt
    }



}