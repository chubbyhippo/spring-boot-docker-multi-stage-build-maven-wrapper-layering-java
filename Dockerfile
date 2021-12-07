FROM bellsoft/liberica-openjdk-alpine:17 as builder
WORKDIR application
COPY .mvn .mvn
COPY mvnw ./
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw package
RUN java -Djarmode=layertools -jar target/*.jar extract

FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
