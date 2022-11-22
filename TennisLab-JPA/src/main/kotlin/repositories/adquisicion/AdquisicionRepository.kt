package repositories.adquisicion

import models.Adquisicion
import repositories.ICRUDRepository
import java.util.UUID

interface AdquisicionRepository: ICRUDRepository<Adquisicion, UUID> {
}