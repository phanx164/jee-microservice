package com.sbux.gred.rest

import com.typesafe.config.ConfigFactory
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
@Path("/version")
open class VersionController {
  @Resource(name = "RestControllerPool") private lateinit var mes: ManagedExecutorService

  private val version = Version(ConfigFactory.load("version.conf").getString("version"))

  @GET
  open fun getVersion(@Suspended res: AsyncResponse) {
    res.setTimeout(100, TimeUnit.MILLISECONDS)
    res.setTimeoutHandler { it.resume(Response.status(Response.Status.REQUEST_TIMEOUT).build()) }
    CompletableFuture.supplyAsync(Supplier { res.resume(Response.ok(version).build()) }, mes)
  }
}
