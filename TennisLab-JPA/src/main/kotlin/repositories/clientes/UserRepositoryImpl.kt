package repositories.clientes

import db.HibernateManager
import db.HibernateManager.manager
import models.User
import java.util.*
import javax.persistence.TypedQuery

class UserRepositoryImpl(User: User) :UserRepository {
    override fun findByEmail(email: String): User? {
        var user: User? = null
        HibernateManager.query {
            user = manager.find(User::class.java, email)
        }
        return user
    }

    override fun findByPhone(phone: String): User? {
        var user: User? = null
        HibernateManager.query {
            user = manager.find(User::class.java, phone)
        }
        return user
    }

    override fun readAll(): List<User> {
        var users = mutableListOf<User>()
        HibernateManager.query {
            val query: TypedQuery<User> = manager.createNamedQuery("User.findAll", User::class.java)
            users = query.resultList
        }
        return users
    }

    override fun findById(id: UUID): User? {
        var user: User? = null
        HibernateManager.query {
            user = manager.find(User::class.java, id)
        }
        return user
    }

    override fun create(entity: User): User {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: User): Boolean {
        var result = false
        HibernateManager.transaction {
            val user = manager.find(User::class.java, entity.id)
            user?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }


}