package repositories.adquisicion

import db.HibernateManager
import db.HibernateManager.manager
import models.Adquisicion
import models.Producto
import models.Tarea
import models.User
import java.util.*
import javax.persistence.TypedQuery

class AdquisicionRepositoryImpl(Adquisicion: Adquisicion, Tarea: Tarea, Producto: Producto, User: User) : AdquisicionRepository {
    override fun readAll(): List<Adquisicion> {
        var adquisiciones = mutableListOf<Adquisicion>()
        HibernateManager.query {
            val query: TypedQuery<Adquisicion> = manager.createNamedQuery("Adquisicion.findAll", Adquisicion::class.java)
            adquisiciones = query.resultList
        }
        return adquisiciones
    }

    override fun findById(id: UUID): Adquisicion? {
        var adquisicion: Adquisicion? = null
        HibernateManager.query {
            adquisicion = manager.find(Adquisicion::class.java, id)
        }
        return adquisicion
    }

    override fun create(entity: Adquisicion): Adquisicion {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Adquisicion): Boolean {
        var result = false
        HibernateManager.transaction {
            val adquisicion = manager.find(Adquisicion::class.java, entity.id)
            adquisicion?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}