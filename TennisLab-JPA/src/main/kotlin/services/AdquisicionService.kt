package services

import dto.AdquisicionDTO
import mappers.TareaMapper
import models.Adquisicion
import models.Producto
import models.Tarea
import models.User
import models.enums.TipoTarea
import repositories.adquisicion.AdquisicionRepositoryImpl
import repositories.tarea.TareaRepositoryImpl
import java.util.UUID

class AdquisicionService: BaseService<Adquisicion, UUID, AdquisicionRepositoryImpl>(
    AdquisicionRepositoryImpl(
        Adquisicion, Tarea, Producto, User
)) {
    val tareaRepo = TareaRepositoryImpl(Tarea, Producto, User)
    val mapper = TareaMapper()

    fun getAllAdquisiciones(): List<AdquisicionDTO> {
        return mapper.toAdquisicionDTO(this.findAll())
    }

    fun getAdquisicionById(id: UUID): AdquisicionDTO? {
        return this.findById(id)?.let { mapper.toAdquisicionDTO(it) }
    }

    fun createAdquisicion(adquisicion: AdquisicionDTO): AdquisicionDTO {
        val tarea = Tarea(
            id = adquisicion.id,
            raqueta = adquisicion.raqueta,
            precio = adquisicion.precio,
            user = adquisicion.user,
            tipoTarea = TipoTarea.ADQUISICION
        )
        tareaRepo.create(tarea)
        return mapper.toAdquisicionDTO(this.insert(mapper.fromAdquisicionDTO(adquisicion)))
    }

    fun deleteAdquisicion(adquisicion: AdquisicionDTO): Boolean {
        return this.delete(mapper.fromAdquisicionDTO(adquisicion))
    }


    fun createAdquisicionInit(adquisicion: AdquisicionDTO): AdquisicionDTO {
        val tarea = Tarea(
            id = adquisicion.id,
            raqueta = adquisicion.raqueta,
            precio = adquisicion.precio,
            user = adquisicion.user,
            tipoTarea = TipoTarea.ADQUISICION
        )
        tareaRepo.create(tarea)
        return mapper.toAdquisicionDTO(repository.create(mapper.fromAdquisicionDTO(adquisicion)))
    }
}