##

```bash
docker exec -it gra.dev bash
cd /app

mvn io.quarkus.platform:quarkus-maven-plugin:3.22.3:create \
  -DprojectGroupId=com.gra \
  -DprojectArtifactId=gra \
  -DclassName="com.gra.api.GoldenRaspberryResource" \
  -Dpath="/awards" \
  -Dextensions="resteasy-reactive-jackson,quarkus-hibernate-orm-panache,quarkus-jdbc-h2"

cd gra

./mvnw quarkus:dev

```






