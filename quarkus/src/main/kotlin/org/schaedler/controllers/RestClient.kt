package org.schaedler.controllers

import jakarta.enterprise.context.ApplicationScoped
import io.quarkus.vertx.web.Body
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ApplicationScoped
class RestClient (@RestClient val client: ExternalService){

    private val logger: Logger = LoggerFactory.getLogger(RestClient::class.java)

    fun isTokenValid(token: String): Boolean {
        val response = client.postRequest(token)
        val responseBody = response.readEntity(Boolean::class.java)
        return responseBody
    }
}
@RegisterRestClient(baseUri="https://spring-hkglgabtia-uc.a.run.app/auth")
interface ExternalService {

        @POST
        @Path("/isTokenValid")
        fun postRequest(@Body token: String): Response
}