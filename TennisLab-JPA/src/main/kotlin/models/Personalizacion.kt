package models

import models.enums.TipoTarea
import org.hibernate.annotations.Type
import java.util.*
import jakarta.persistence.*

@Entity
//@Table(name = "PERSONALIZACIONES")
@NamedQuery(name = "Personalizacion.findAll", query = "SELECT p FROM Personalizacion p")
class Personalizacion(): Tarea() {
    @Id @GeneratedValue()
    @Type(type = "uuid-char")
    override var id: UUID = super.id
    @Column(name = "peso")
    var peso: Int = 0
    @Column(name = "balance")
    var balance: Double = 0.0
    @Column(name = "rigidez")
    var rigidez: Int = 0

    constructor(
        id: UUID?,
        raqueta: Producto,
        user: User,
        peso: Int,
        balance: Double,
        rigidez: Int
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.raqueta = raqueta
        this.user = user
        this.peso = peso
        this.balance = balance
        this.rigidez = rigidez
        this.precio = 60.0
        this.tipoTarea = TipoTarea.PERSONALIZACION
    }

    constructor(
        id: UUID?,
        peso: Int,
        balance: Double,
        rigidez: Int
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.peso = peso
        this.balance = balance
        this.rigidez = rigidez
        this.precio = 60.0
        this.tipoTarea = TipoTarea.PERSONALIZACION
    }
}
