# meecrowave-kotlin-example

Example maven project for Meecrowave using Kotlin

# What is it?

This is an example REST API app built on meecrowave and Kotlin, with support for OpenAPI Spec (Swagger).

# Install

Simply run `mvn clean package install meecrowave:run` to make sure things compile and run.

# The example REST application

This app is a simple Cars REST API exposing a `GET /cars?make={ford,toyota,chevrolet}` and `GET /cars/ping` endpoint.

There are really two main classes to look at:

- CarController
- CarService

Using CDI, CarService is injected into CarController, and both are `@ApplicationScoped`.

You may also notice the use of `AsyncResponse` for non-blocking behaviour so that the actual work happens off-thread.

# Running

If you remove the `meecrowave-core` dependency, you can compile you app as a war and not run as an uberjar.

`java -jar meecrowave-runner-0.2.0.jar --webapp=yourapp.war`

This will deploy your war app giving an even smaller build. UberJar is pretty handy though!

```
java -Xms96m -Xmx96m -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9000 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -jar example.jar --http=8080 --host=127.0.0.1
```

The command above will launch your uberjar build and enable remote jmx so that you can use something like `VisualVM` to monitor memory usage, threads, classes loaded, etc.

# Run docker

```
docker run -it --rm \
    --publish 8080:8080 \
    --memory 256M \
    --cpus 2 \
    --env JAVA_MIN_MEM=64M \
    --env JAVA_MAX_MEM=128M \
    --env GRED_ENV=dev \
    --env GRED_DC=chd \
    platform/test-api-v1:latest
```

```
artillery quick --duration 60 --rate 10 -n 20 "http://192.168.1.18:8080/api"
```
