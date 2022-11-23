package services

import dto.UserDTO
import mappers.UserMapper
import models.User
import repositories.clientes.UserRepositoryImpl
import java.util.UUID

class UserService: BaseService<User, UUID, UserRepositoryImpl>(UserRepositoryImpl(User)) {
    val mapper = UserMapper()

    fun getAllUsers(): List<UserDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getUserById(id: UUID): UserDTO? {
        return this.findById(id)?.let { mapper.toDTO(it) }
    }

    fun getUserByMail(mail: String): UserDTO? {
        return repository.findByEmail(mail)?.let { mapper.toDTO(it) }
    }

    fun getUserByPhone(phone: String): UserDTO? {
        return repository.findByPhone(phone)?.let { mapper.toDTO(it) }
    }

    fun createUser(user: UserDTO): UserDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(user)))
    }

    fun deleteUser(user: UserDTO): Boolean {
        return this.delete(mapper.fromDTO(user))
    }

    fun createUserInit(user: UserDTO): UserDTO {
        return mapper.toDTO(repository.create(mapper.fromDTO(user)))
    }
}