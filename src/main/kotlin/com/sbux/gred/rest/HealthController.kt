package com.sbux.gred.rest

import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import java.util.function.Supplier
import javax.annotation.Resource
import javax.enterprise.concurrent.ManagedExecutorService
import javax.faces.bean.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.container.AsyncResponse
import javax.ws.rs.container.Suspended
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@ApplicationScoped
@Produces(APPLICATION_JSON)
@Path("/health")
open class HealthController {
  @Resource(name = "RestControllerPool") private lateinit var mes: ManagedExecutorService

  @GET
  open fun getHealth(@Suspended res: AsyncResponse) {
    res.setTimeout(100, TimeUnit.MILLISECONDS)
    res.setTimeoutHandler { it.resume(Response.status(Response.Status.REQUEST_TIMEOUT).build()) }
    CompletableFuture.supplyAsync(Supplier {
      res.resume(Response.ok(Health("pong", LocalDateTime.now().toString())).build())
    }, mes)
  }
}
