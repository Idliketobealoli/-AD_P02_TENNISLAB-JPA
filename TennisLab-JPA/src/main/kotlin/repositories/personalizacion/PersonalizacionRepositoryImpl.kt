package repositories.personalizacion

import db.HibernateManager
import models.Personalizacion
import models.Producto
import models.Tarea
import models.User
import java.util.*
import javax.persistence.TypedQuery

class PersonalizacionRepositoryImpl(personalizacion: Personalizacion, tarea: Tarea, producto: Producto, user: User) : PersonalizacionRepository {
    override fun readAll(): List<Personalizacion> {
        var personalizacions = mutableListOf<Personalizacion>()
        HibernateManager.query {
            val query: TypedQuery<Personalizacion> = HibernateManager.manager.createNamedQuery("Personalizacion.findAll", Personalizacion::class.java)
            personalizacions = query.resultList
        }
        return personalizacions
    }

    override fun findById(id: UUID): Personalizacion? {
        var personalizacion: Personalizacion? = null
        HibernateManager.query {
            personalizacion = HibernateManager.manager.find(Personalizacion::class.java, id)
        }
        return personalizacion
    }

    override fun create(entity: Personalizacion): Personalizacion {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Personalizacion): Boolean {
        var result = false
        HibernateManager.transaction {
            val personalizacion = HibernateManager.manager.find(Personalizacion::class.java, entity.id)
            personalizacion?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}