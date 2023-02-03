# Border Waiting Area Notifier

## Reasons and tasks
Creating this application I'm planning to solve first of all my private need.

While being in border waiting area you have to know status of the queue and your order in it. For this reason you have to watch on panel or refresh website https://mon.declarant.by/zone . On a panel you can see only those car plates that can go to customs barrier but actual status is available only online.

To know your actual order you have to refresh webpage everytime. So we have several problems with this
1. To get actual info you need to manually refresh page
2. By default website displays lorries info. To get car info you have to switch to cars in settings and then list to your info or filter by typing plate number. This mean you need to do additional manipulations everytime you do a refresh
3. There are places with bad internet coverage.

As a result of this three points we have a every minute need to do page update.

Application task is to collect actual data and inform users in case of order and status change. Expect to inform through telegram bot.

Task looks simple but give opportunity to practice with
1. Use of Quarkus framework (for experience reason here not because of any competitive advantages)
2. Implement microservice architecture
3. Use message broker to deliver messages between scrapper and telegram bot
4. Practice with telegram bot implementation

---

## Quarkus
QuarkusIO, the Supersonic Subatomic Java, promises to deliver small artifacts, extremely fast boot time, and lower time-to-first-request. When combined with GraalVM, Quarkus will compile ahead-of-time (AOT).

The generates three Dockerfile files to build a Docker container for the JAR file or the native executable.

Dockerfile.fast-jar – Build a docker container for the JAR file, this is a new packaging format (Fast-jar) since Quarkus 1.5 released, for faster startup times. Looks like now this is legacy image
Dockerfile.jvm – Build a docker container for the JAR file.
Dockerfile.native – Build a docker container for native executable.


---

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/border-waiting-area-notifier-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
