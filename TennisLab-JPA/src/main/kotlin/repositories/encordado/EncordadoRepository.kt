package repositories.encordado

import models.Adquisicion
import models.Encordado
import repositories.ICRUDRepository
import java.util.UUID

interface EncordadoRepository: ICRUDRepository<Encordado, UUID> {
}