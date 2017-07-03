package com.sbux.gred.domain

import java.util.concurrent.CompletableFuture
import java.util.function.Supplier
import javax.annotation.Resource
import javax.enterprise.concurrent.ManagedExecutorService

open class CarService {
  @Resource(name = "ServicePool") private lateinit var mes: ManagedExecutorService

  private val cars = arrayOf(
    Car(CarMake.FORD, "Pinto", "Red"),
    Car(CarMake.TOYOTA, "Camry", "Blue"),
    Car(CarMake.TOYOTA, "Camry", "Blue"),
    Car(CarMake.TOYOTA, "Camry", "Blue"),
    Car(CarMake.TOYOTA, "Camry", "Blue"),
    Car(CarMake.FORD, "Pinto", "Red"),
    Car(CarMake.CHEVROLET, "Camaro", "Blue"),
    Car(CarMake.FORD, "Pinto", "Red"),
    Car(CarMake.CHEVROLET, "Camaro", "Blue")
  )

  open fun findCars(filter: CarFilter): CompletableFuture<Cars> {
    return CompletableFuture.supplyAsync<Cars>(Supplier {
      Cars(
        cars
          .filter { filter.make.isNullOrEmpty() || filter.make?.toLowerCase() == it.make.toString().toLowerCase() }
          .take(filter.limit),
        cars.size
      )
    }, mes)
  }
}
