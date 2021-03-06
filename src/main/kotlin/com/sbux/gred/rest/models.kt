package com.sbux.gred.rest

import javax.json.bind.annotation.JsonbProperty

internal data class Health(val message: String, val timestamp: String)
internal data class Version(val version: String = "")
internal data class Car(@get:JsonbProperty("customMake") val make: String, val model: String, val color: String)
internal data class Cars(val cars: List<Car>, val paging: Paging)
internal data class Paging(val total: Int, val limit: Int, val offset: Int)
