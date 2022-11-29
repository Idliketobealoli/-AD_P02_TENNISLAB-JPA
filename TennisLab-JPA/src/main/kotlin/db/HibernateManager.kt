package db

import java.io.Closeable
import java.sql.SQLException
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityTransaction
import jakarta.persistence.Persistence

object HibernateManager: Closeable {

    private var entityManagerFactory = Persistence.createEntityManagerFactory("default")
    var manager: EntityManager
    private var transaction: EntityTransaction

    init {
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    fun open() {
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    override fun close() {
        manager.close()
    }

    fun query(operations: () -> Unit) {
        open()
        try {
            operations()
        } catch (e: SQLException) {
            println("Error en la consulta: ${e.message}")
        }finally {
            close()
        }
    }

    fun transaction(operations: () -> Unit) {
        open()
        try {
            transaction.begin()
            operations()
            transaction.commit()
        }catch (e: SQLException) {
            println("Error en la transacción: ${e.message}")
        }finally {
            close()
        }
    }
}