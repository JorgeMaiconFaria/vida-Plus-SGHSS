# Vida Plus SGHSS
Trabalho da Faculdade referente ao estudo de caso da empresa fictícia VidaPlus.

## Rodar a aplicação no modo desenvolvedor

```shell script
./mvnw quarkus:dev
```

> **_NOTA:_**  Acesse a interface do desenvolvedor no link: <http://localhost:8080/q/dev/>.

## Empacotando e executando a aplicação

A aplicação pode ser empacotada utilizando o comando:

```shell script
./mvnw package
```

Esse comando gera o arquivo `quarkus-run.jar` dentro do diretório `target/quarkus-app/`.
Atenção: esse não é um _über-jar_ ,pois as dependências são copiadas para o diretório `target/quarkus-app/lib/`.

A aplicação pode ser executada com `java -jar target/quarkus-app/quarkus-run.jar`.

Se você quiser gerar um _über-jar_, execute o seguinte comando:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

A aplicação, empacotada como um  _über-jar_, pode ser executada com `java -jar target/*-runner.jar`.

## Criando um executável nativo

Você pode criar um executável nativo utilizando:

```shell script
./mvnw package -Dnative
```

Ou, se você não tiver o GraalVM instalado, pode construir o executável nativo em um contêiner com:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Depois disso, o executável nativo pode ser executado com: `./target/gestao-hospitalar-1.0.0-SNAPSHOT-runner`

Se quiser aprender mais sobre como criar executáveis nativos, consulte <https://quarkus.io/guides/maven-tooling>.

## Testando a aplicação
A execução para testes funciona perfeitamente apenas iniciando a aplicação no modo desenvolvedor, como mencionado a cima,
 sem a necessidade de empacotar a aplicação.

Esse projeto foi desenvolvido com o sistema de autenticação baseada em tokens JWT e controle de acesso por papéis,
por isso, a aplicação cria um usuário administrador para ser possível acessar os endpoints.

As credenciais desse usuário são: "email": "admin@admin.com",
"senha": "admin123"

O banco de dados usado foi o MySQL, a aplicação tentará criar as tabelas usadas na aplicação ao inicia-lá.
Por favor, certifique-se que o usuário e senha do banco de dados da máquina cuja aplicação irá ser iniciada sejam iguais 
aos apresentado no arquivo `application.properties` no seguinte caminho: `src/main/resources/META-INF.resources`.

Usuário e senha vigentes no arquivo:
`quarkus.datasource.username=${DB_USER:root} 
quarkus.datasource.password=${DB_PASSWORD:root}`

Neste caso, o usuário e senha padrão são root.

Se sua máquina conter variáveis de ambiente configuradas para o usuário e senha do banco de dados não é necessária essa configuração. 
Em caso de divergencia, por favor, altere as configurações para que possa ser possível o teste da aplicação.

Juntamento com os arquivos do projeto estão a documentação dos endpoints e um arquivo JSON com os testes prontos para 
importar no Postman.