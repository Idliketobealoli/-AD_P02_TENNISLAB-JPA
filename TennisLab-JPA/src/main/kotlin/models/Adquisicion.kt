package models

import models.enums.TipoTarea
import org.hibernate.annotations.Type
import java.util.*
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.NamedQuery
import jakarta.persistence.OneToMany

@Entity
@NamedQuery(name = "Adquisicion.findAll", query = "SELECT a FROM Adquisicion a")
class Adquisicion(): Tarea() {
    @Id @GeneratedValue
    @Type(type = "uuid-char")
    override var id: UUID = super.id
    // TODO revisar la relación adquisición-producto
    @OneToMany() @Column(name = "producto_adquirido") @Embedded
    lateinit var productoAdquirido: Producto

    constructor(
        id: UUID?,
        raqueta: Producto,
        user: User,
        productoAdquirido: Producto,
        precio: Double
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.raqueta = raqueta
        this.user = user
        this.productoAdquirido = productoAdquirido
        this.precio = precio
        this.tipoTarea = TipoTarea.ADQUISICION
    }

    constructor(
        id: UUID?,
        productoAdquirido: Producto,
        precio: Double
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.productoAdquirido = productoAdquirido
        this.precio = precio
        this.tipoTarea = TipoTarea.ADQUISICION
    }
}
