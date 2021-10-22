@file:Suppress("JpaObjectClassSignatureInspection")

package com.example

import io.micronaut.runtime.*
import io.micronaut.transaction.annotation.*
import jakarta.inject.*
import java.util.*
import javax.persistence.*
import javax.transaction.*
import javax.validation.constraints.*


@Entity
@Table(name = "genre")
public class Genre(
    @Id
    val id: String
) {
    override fun toString(): String {
        return "Genre(id='$id')"
    }
}

interface GenreRepository {
    fun save(name: String): Genre
    fun findAll(): List<Genre>
}

@Singleton
class GenreRepositoryImpl(
    private val entityManager: EntityManager,
    private val applicationConfiguration: ApplicationConfiguration
) : GenreRepository {

    @TransactionalAdvice
    override fun save(name: @NotBlank String): Genre {
        val genre = Genre(name!!)
        entityManager.persist(genre)
        return genre
    }

//    @Transactional
//    fun deleteById(@NotNull id: Long?) {
//        findById(id).ifPresent { entity: Any? -> entityManager.remove(entity) }
//    }

    @ReadOnly
    override fun findAll(): List<Genre> {
        var qlString = "SELECT g FROM Genre as g"
        val query = entityManager.createQuery(qlString, Genre::class.java)
        return query.resultList
    }

//    @Transactional
//    fun update(@NotNull id: Long?, name: @NotBlank String?): Int {
//        return entityManager.createQuery("UPDATE Genre g SET name = :name where id = :id")
//            .setParameter("name", name)
//            .setParameter("id", id)
//            .executeUpdate()
//    }
//
//    @Transactional
//    fun saveWithException(name: @NotBlank String?): Genre {
//        save(name)
//        throw PersistenceException()
//    }

    companion object {
        private val VALID_PROPERTY_NAMES = Arrays.asList("id", "name")
    }
}
