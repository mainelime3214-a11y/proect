FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY aqa-shop.jar app.jar
# Команда запуска остается прежней
CMD ["java", "-jar", "app.jar"]