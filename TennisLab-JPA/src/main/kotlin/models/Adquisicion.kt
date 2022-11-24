package models

import models.enums.TipoTarea
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.persistence.OneToMany

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
