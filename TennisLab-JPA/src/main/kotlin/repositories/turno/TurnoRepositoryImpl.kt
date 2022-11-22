package repositories.turno

import db.HibernateManager
import models.Turno
import java.util.*
import javax.persistence.TypedQuery

class TurnoRepositoryImpl: TurnoRepository {
    override fun readAll(): List<Turno> {
        var turnos = mutableListOf<Turno>()
        HibernateManager.query {
            val query: TypedQuery<Turno> = HibernateManager.manager.createNamedQuery("Turno.findAll", Turno::class.java)
            turnos = query.resultList
        }
        return turnos
    }

    override fun findById(id: UUID): Turno? {
        var turno: Turno? = null
        HibernateManager.query {
            turno = HibernateManager.manager.find(Turno::class.java, id)
        }
        return turno
    }

    override fun create(entity: Turno): Turno {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Turno): Boolean {
        var result = false
        HibernateManager.transaction {
            val turno = HibernateManager.manager.find(Turno::class.java, entity.id)
            turno?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}