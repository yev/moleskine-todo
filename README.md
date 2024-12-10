![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)
![Vuetify](https://img.shields.io/badge/Vuetify-1867C0?style=for-the-badge&logo=vuetify&logoColor=AEDDFF)
![Vite](https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white)

# Spring Boot app 
pet project for practicing technologies after **devoxx2024** at 📍Antwerpen

used technologies:
- Java 23
- multi maven project
- SpringBoot 3.4
- Open APi / Swagger generation for controllers and web models
- Lombok
- MapStruct
- hibernate envers for entity auditing
- FlyWay
- buildScan from #Gradle
- TestContainers
- Vuetify UI for simple UI display of all todos
- PiTest for mutational testing experiments

## Kafka

Run the Spring Boot with profile **kafka**

1. run kafka container
 ``` 
  docker run -p --name moleskine-kafka 9092:9092 apache/kafka:3.9.0
  ```
2. go inside kafka container
 ```
  docker exec --workdir /opt/kafka/bin/ -it moleskine-kafka sh
 ```
3. inside container run the console producer script 
```
./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test-topic
```
4. type any world and Enter

