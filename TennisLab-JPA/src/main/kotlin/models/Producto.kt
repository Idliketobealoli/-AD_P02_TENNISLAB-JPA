package models

import models.enums.TipoProducto
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*


/**
 * @author Iván Azagra Troya
 * Clase POKO de la entidad producto
 */
@Entity
@Table(name = "PRODUCTOS")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto")
/*data */class Producto(
    /*@Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @Type(type = "uuid-char")
    val id: UUID = UUID.randomUUID(),*/
    /*val tipoProducto: TipoProducto,
    val marca: String,
    val modelo: String,
    var precio: Double = 0.0,
    var stock: Int = 0*/
) {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @Type(type = "uuid-char")
    lateinit var id: UUID
    lateinit var tipoProducto: TipoProducto
    lateinit var marca: String
    lateinit var modelo: String
    var precio: Double = 0.0
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
