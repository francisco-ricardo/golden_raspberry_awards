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

## Considerações

As seguintes premissas foram assumidas:

- Os cálculos de intervalos devem ser baseados exclusivamente em premiações consecutivas, sem considerar 
intervalos entre premiações não contíguas.

- Se houver somente 1 *Produtor* que tenha conseguido mais de uma premiação, este fará parte 
tanto do grupo `min` quanto do grupo `max`.

- O campo `producers` foi considerado como sendo do tipo `String` e não uma lista.
Portanto, pode ocorrer de um determinado *Produtor* estar em composições diferentes com outros 
produtores e não ser contabilizado como sendo ganhador consecutivo.

- O campo `winner` do model `Movie` será definido como `true`, sempre que o seu respectivo valor no 
arquivo CSV for `yes` (case insensitive). Para os demais casos, será considerado como `false`.

- Foi criado somente o profile *dev*, não considerando cobertura de testes, deploy para a nuvem, pipelines CI/CD, 
entre outros aspectos.


