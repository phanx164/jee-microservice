package com.brandonlamb.example.rest

import com.brandonlamb.example.domain.CarFilter
import com.brandonlamb.example.domain.CarService
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import java.util.function.Supplier
import javax.annotation.Resource
import javax.enterprise.concurrent.ManagedExecutorService
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.container.AsyncResponse
import javax.ws.rs.container.Suspended
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import com.brandonlamb.example.domain.Car as CarDto

@ApplicationScoped
@Path("/")
@Produces(APPLICATION_JSON)
open class RestEndpoint {
  @Resource(name = "CarControllerPool") private lateinit var mes: ManagedExecutorService
  @Inject private lateinit var carService: CarService

  @GET
  open fun getCars(
    @QueryParam("make") make: String?,
    @QueryParam("limit") @DefaultValue("10") limit: Int,
    @QueryParam("offset") @DefaultValue("0") offset: Int,
    @Suspended res: AsyncResponse
  ) {
    res.setTimeout(500, TimeUnit.MILLISECONDS)
    res.setTimeoutHandler { it.resume(Response.status(Response.Status.REQUEST_TIMEOUT).build()) }

    CompletableFuture.supplyAsync(Supplier {
      carService.findCars(CarFilter(make, limit, offset)).thenAccept {
        res.resume(Response.ok(Cars(
          it.cars.map { Car(it.make.toString().toLowerCase(), it.model, it.color) },
          Paging(it.total, limit, offset)
        )).build())
      }
    }, mes)
  }
}
