To start this project you should install Docker and execute the following lines in your terminal

mvn clean install -f jmp-spring-core/pom.xml

docker build -t spring-core .

docker run -it -p 80:80 spring-core

Or execute startup.sh shell script
