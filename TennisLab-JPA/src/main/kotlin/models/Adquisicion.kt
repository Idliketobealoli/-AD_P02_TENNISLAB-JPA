package models

import models.enums.TipoTarea
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@NamedQuery(name = "Adquisicion.findAll", query = "SELECT a FROM Adquisicion a")
class Adquisicion(): Tarea() {
    @Id @GeneratedValue
    @Type(type = "uuid-char")
    override var id: UUID = super.id
    // TODO revisar la relación adquisición-producto
    @ManyToOne
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
