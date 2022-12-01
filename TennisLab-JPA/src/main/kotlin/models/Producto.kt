package models

import javax.persistence.*
import models.enums.TipoProducto
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*


/**
 * @author Iván Azagra Troya
 * Clase POKO de la entidad producto
 */
@Entity
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
class Producto() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    lateinit var id: UUID
    @Column @Embedded // todo enum puede ser @Embeddable??
    lateinit var tipoProducto: TipoProducto
    @Column
    lateinit var marca: String
    @Column
    lateinit var modelo: String
    @Column
    var precio: Double = 0.0
    @Column
    var stock: Int = 0

    constructor(
        id: UUID?,
        tipoProducto: TipoProducto,
        marca: String,
        modelo: String,
        precio: Double,
        stock: Int?
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.tipoProducto = tipoProducto
        this.marca = marca
        this.modelo = modelo
        this.precio = precio
        this.stock = stock ?: 0
    }
}
