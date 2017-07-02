package com.brandonlamb.example.rest

import javax.json.bind.annotation.JsonbProperty

internal data class Car(@get:JsonbProperty("customMake") val make: String, val model: String, val color: String)
internal data class Cars(val cars: List<Car>, val paging: Paging)
internal data class Paging(val total: Int, val limit: Int, val offset: Int)
internal data class Version(val version: String)
