package repositories.tarea

import db.HibernateManager
import models.Producto
import models.Tarea
import models.User
import java.util.*
import javax.persistence.TypedQuery

class TareaRepositoryImpl(Tarea: Tarea, Producto: Producto, User: User) : TareaRepository {
    override fun readAll(): List<Tarea> {
        var tareas = mutableListOf<Tarea>()
        HibernateManager.query {
            val query: TypedQuery<Tarea> = HibernateManager.manager.createNamedQuery("Tarea.findAll", Tarea::class.java)
            tareas = query.resultList
        }
        return tareas
    }

    override fun findById(id: UUID): Tarea? {
        var tarea: Tarea? = null
        HibernateManager.query {
            tarea = HibernateManager.manager.find(Tarea::class.java, id)
        }
        return tarea
    }

    override fun create(entity: Tarea): Tarea {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Tarea): Boolean {
        var result = false
        HibernateManager.transaction {
            val tarea = HibernateManager.manager.find(Tarea::class.java, entity.id)
            tarea?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}