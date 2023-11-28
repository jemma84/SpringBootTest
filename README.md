1.download docker-image from dockerhub
2.run next command: docker run -p 8084:8081 jemma84/springboot-sector-task
3.in browser type: http://localhost:8084/h2
4.click connect
5.from resources/db copy and run data.sql in h2 console
6.go to localhost:8084/login and try to login as bender/1qaz

To run the PostgreSQL you need to execute the docker-compose.yml. Example in the terminal:

```shell script
docker-compose up -d