package com.example

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.*
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.inject.*
import javax.validation.ConstraintViolationException

@Singleton
class HomeRoute(
    private val nameTransformer: NameTransformer,
    @Inject var genreRepository: GenreRepository
) : KtorRoutingBuilder({

    post("/") {
        val name = call.receive<NameRequest>().name
        try {
            call.respondText(nameTransformer.transform(name), contentType = ContentType.Text.Plain)
        } catch (e: ConstraintViolationException) {
            call.respondText(
                e.constraintViolations.joinToString(",") { c -> "${c.propertyPath.last().name} ${c.message}" },
                contentType = ContentType.Text.Plain,
                status = HttpStatusCode.UnprocessableEntity
            )
        }
    }
    get("/findAll") {
        println(genreRepository.findAll())
        println(genreRepository.findAll().first())
        call.respond(HttpStatusCode.OK)
    }

    get("/save/{id}"){
        val s = call.parameters["id"]
        genreRepository.save("asd$s")

        println("genreRepository.save()")
        call.respond(HttpStatusCode.OK)
    }
})

data class NameRequest(val name: String)


/**
 * ktor + micro
 * hibernate
 */
