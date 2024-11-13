package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {
    @PutMapping("/{taskID}/tasks/{taskNewName}")
    fun renameTask(@PathVariable taskID: UUID, @PathVariable taskNewName: String) : TaskRenamedEvent {
        return taskEsService.update(taskID) {
            it.rename(taskNewName)
        }
    }
    @PutMapping("/close/{taskID}")
    fun close(@PathVariable taskID: UUID) : TaskClosedEvent {
        return taskEsService.update(taskID) {
            it.close()
        }
    }
    @PutMapping("/reopen/{taskID}")
    fun reopen(@PathVariable taskID: UUID) : TaskReopenedEvent {
        return taskEsService.update(taskID) {
            it.reopen()
        }
    }
    @PutMapping("/{taskID}/status/{statusId}")
    fun changeTaskStatus(@PathVariable taskID: UUID,@PathVariable statusId: UUID) : TaskStatusChangedEvent {
        return taskEsService.update(taskID) {
            it.changeTaskStatus(statusId)
        }
    }
}