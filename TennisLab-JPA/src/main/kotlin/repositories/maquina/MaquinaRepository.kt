package repositories.maquina

import models.Adquisicion
import models.Maquina
import repositories.ICRUDRepository
import java.util.UUID

interface MaquinaRepository: ICRUDRepository<Maquina, UUID> {
}