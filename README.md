1.download docker-image from dockerhub
2.run next command: docker run -p 8084:8081 jemma84/springboot-sector-task
## Running

To run the sample, execute the following command in a repository's root directory:

```bash
./gradlew bootRun
```
6.go to http://localhost:8082 and try to login as bender/1qaz

To run the PostgreSQL you need to execute the docker-compose.yml. Example in the terminal:

```shell script
docker-compose up -d