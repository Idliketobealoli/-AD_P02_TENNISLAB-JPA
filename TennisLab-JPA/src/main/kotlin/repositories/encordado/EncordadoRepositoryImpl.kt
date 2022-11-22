package repositories.encordado

import db.HibernateManager
import db.HibernateManager.manager
import models.Encordado
import java.util.*
import javax.persistence.TypedQuery

class EncordadoRepositoryImpl: EncordadoRepository {
    override fun readAll(): List<Encordado> {
        var encordado = mutableListOf<Encordado>()
        HibernateManager.query {
            val query: TypedQuery<Encordado> = manager.createNamedQuery("Encordado.findAll", Encordado::class.java)
            encordado = query.resultList
        }
        return encordado
    }

    override fun findById(id: UUID): Encordado? {
        var encordado: Encordado? = null
        HibernateManager.query {
            encordado = manager.find(Encordado::class.java, id)
        }

        return encordado
    }

    override fun create(entity: Encordado): Encordado {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Encordado): Boolean {
        var result = false
        HibernateManager.transaction {
            val encordado = manager.find(Encordado::class.java, entity.id)
            encordado?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}