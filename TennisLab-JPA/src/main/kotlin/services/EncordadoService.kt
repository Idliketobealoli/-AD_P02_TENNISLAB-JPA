package services

import dto.EncordadoDTO
import entities.EncordadoDao
import entities.ProductoDao
import entities.TareaDao
import entities.UserDao
import mappers.TareaMapper
import models.Encordado
import models.Producto
import models.Tarea
import models.User
import models.enums.TipoTarea
import repositories.encordado.EncordadoRepositoryImpl
import repositories.tarea.TareaRepositoryImpl
import java.util.UUID

class EncordadoService: BaseService<Encordado, UUID, EncordadoRepositoryImpl>(
    EncordadoRepositoryImpl(
        Tarea, Producto, User, Encordado
)) {
    val tareaRepo = TareaRepositoryImpl(Tarea, Producto, User)
    val mapper = TareaMapper()

    fun getAllEncordados(): List<EncordadoDTO> {
        return mapper.toEncordadoDTO(this.findAll())
    }

    fun getEncordadoById(id: UUID): EncordadoDTO? {
        return this.findById(id)?.let { mapper.toEncordadoDTO(it) }
    }

    fun createEncordado(encordado: EncordadoDTO): EncordadoDTO {
        val tarea = Tarea(
            id = encordado.id,
            raqueta = encordado.raqueta,
            precio = encordado.precio,
            user = encordado.user,
            tipoTarea = TipoTarea.ENCORDADO
        )
        tareaRepo.create(tarea)
        return mapper.toEncordadoDTO(this.insert(mapper.fromEncordadoDTO(encordado)))
    }

    fun deleteEncordado(encordado: EncordadoDTO): Boolean {
        return this.delete(mapper.fromEncordadoDTO(encordado))
    }


    fun createEncordadoInit(encordado: EncordadoDTO): EncordadoDTO {
        val tarea = Tarea(
            id = encordado.id,
            raqueta = encordado.raqueta,
            precio = encordado.precio,
            user = encordado.user,
            tipoTarea = TipoTarea.ENCORDADO
        )
        tareaRepo.create(tarea)
        return mapper.toEncordadoDTO(repository.create(mapper.fromEncordadoDTO(encordado)))
    }
}