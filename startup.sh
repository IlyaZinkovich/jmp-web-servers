mvn clean install -f jmp-spring-core/pom.xml
docker build -t spring-core .
docker run -it -p 80:80 spring-core
