package ru.quipy.projections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "tasks")
data class TaskProjection(
    @Id
    val taskId: UUID,
    var name: String,
    val isOpen: Boolean,
    var statusId: UUID,
    val users: List<UserProjection> = emptyList<UserProjection>(),
)

data class TaskExecutorProjection(
    val executorId: UUID,
)