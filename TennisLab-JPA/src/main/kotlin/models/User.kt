package models

import java.util.UUID
import javax.persistence.*

@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
class User() {
    @Id /*@GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Type(type = "uuid-char")*/
    @Column
    lateinit var id:UUID
    @Column
    lateinit var nombre: String
    @Column
    lateinit var apellido: String
    @Column
    lateinit var telefono: String
    @Column
    lateinit var email: String
    lateinit var password: String
    @Column
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