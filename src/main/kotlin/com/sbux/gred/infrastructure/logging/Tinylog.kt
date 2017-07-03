package com.sbux.gred.infrastructure.logging

import org.pmw.tinylog.Configurator
import org.pmw.tinylog.Logger
import javax.annotation.PreDestroy
import javax.ejb.Startup
import javax.enterprise.context.ApplicationScoped

@Startup
@ApplicationScoped
open class Tinylog {
  @PreDestroy open fun preDestroy() {
    Logger.info("Shutting down Tinylog")
    Configurator.shutdownWritingThread(true)
  }
}
