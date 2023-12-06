1.download docker-image from dockerhub
2.run next command: docker run -p 8084:8082 jemma84/demo, login as 
## Running

To run the sample locally, execute the following command in a repository's root directory:

```bash
./gradlew bootRun
```
6.go to http://localhost:8082 and try to login as bender/1qaz or create new user

To run the PostgreSQL locally you need to execute the postgres.yml. 
To build the docker image run:

```bash
  ./gradlew bootBuildImage
```
To start the application with docker compose:

```bash
  docker compose up -d
```

