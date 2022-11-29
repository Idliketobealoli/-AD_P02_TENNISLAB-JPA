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
@Table(name = "PRODUCTOS")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto")
@Embeddable
class Producto() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @Type(type = "uuid-char")
    lateinit var id: UUID
    @Column(name = "tipo_producto") @Embedded
    lateinit var tipoProducto: TipoProducto
    @Column(name = "marca")
    lateinit var marca: String
    @Column(name = "modelo")
    lateinit var modelo: String
    @Column(name = "precio")
    var precio: Double = 0.0
    @Column(name = "stock")
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
