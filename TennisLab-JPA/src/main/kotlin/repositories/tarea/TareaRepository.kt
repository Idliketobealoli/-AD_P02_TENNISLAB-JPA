package repositories.tarea

import models.Adquisicion
import models.Tarea
import repositories.ICRUDRepository
import java.util.UUID

interface TareaRepository: ICRUDRepository<Tarea, UUID> {
}