FROM java:8
EXPOSE 8080
ADD /target/springbootdockerpost.jar springbootdockerpost.jar
ENTRYPOINT ["java","-jar","springbootdockerpost.jar"]
