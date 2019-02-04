Start jar with:
	java -jar target/workshop-api-1.0-SNAPSHOT.jar server configuration.yml

On heroku: 
	java -Dserver.port=$PORT $JAVA_OPTS -jar target/workshop-api-1.0-SNAPSHOT.jar server configuration.yml