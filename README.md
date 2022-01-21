## Start docker container
```
docker-compose up -d
```

## Compile
```
docker exec openjdk javac --release 8 Main.java
```

## Run binary
```
docker exec -it openjdk java Main
```

## Creating an executable jar file
```
docker exec -it openjdk jar cvfe CriptografiaBasica.jar Main *.class
```

## Run jar
```
java -jar CriptografiaBasica.jar
```

## Volume openjdk-16
```
/var/lib/docker/volumes/criptografia-basica_openjdk-16/_data
```