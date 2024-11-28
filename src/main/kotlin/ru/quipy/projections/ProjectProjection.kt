package ru.quipy.projections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "projects")
data class ProjectProjection(
    @Id
    val projectId: UUID,
    val projectTitle: String,
    val creatorId: String,
    val tasks: Map<UUID, TaskProjection> = emptyMap(),
    val users: List<UserProjection> = emptyList<UserProjection>(),

    val participants: MutableList<ParticipantProjection>
)

data class ParticipantProjection(
    val userId: String,
)