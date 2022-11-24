package models

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
class User() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column(name = "id")
    @Type(type = "uuid-char")
    lateinit var id:UUID
    @Column(name = "nombre")
    lateinit var nombre: String
    @Column(name = "apellido")
    lateinit var apellido: String
    @Column(name = "telefono")
    lateinit var telefono: String
    @Column(name = "email")
    lateinit var email: String
    lateinit var password: String
    @Column(name = "perfil")
    lateinit var perfil: String

    constructor(
        id: UUID?,
        nombre: String,
        apellido: String,
        telefono: String,
        email: String,
        password: String,
        perfil: String
    ) : this(){
        this.id = id ?: UUID.randomUUID()
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.email = email
        this.password = password
        this.perfil = perfil
    }
}