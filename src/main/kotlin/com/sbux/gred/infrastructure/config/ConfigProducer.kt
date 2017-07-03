package com.sbux.gred.infrastructure.config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

open class ConfigProducer {
  @ApplicationScoped
  @Produces
  open fun createConfig(): Config = ConfigFactory.load()
}
