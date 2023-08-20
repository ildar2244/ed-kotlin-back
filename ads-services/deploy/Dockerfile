
FROM gradle:7-jdk11 AS build
ENV TM_TOKEN_BOT="telegram_bot_token" VK_TOKEN_BOT="vk_access_token"
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ed-project.jar
ENTRYPOINT ["java","-jar","/app/ed-project.jar"]