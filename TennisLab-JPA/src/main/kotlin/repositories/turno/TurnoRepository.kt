package repositories.turno

import models.Turno
import repositories.ICRUDRepository
import java.util.UUID

interface TurnoRepository: ICRUDRepository<Turno, UUID> {
}