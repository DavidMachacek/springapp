# ###################################################################################################
# Dockerfile for service/application image based on jar based service base image enriched by project's app artifact
# ###################################################################################################

FROM java:8

EXPOSE 8080 9990

COPY ./target/demo-*.jar /app.jar

HEALTHCHECK --interval=30s \
            --timeout=10s \
            --retries=3 \
            --start-period=20s \
             CMD ["bash", "-c", "curl -i -s http://127.0.0.1:8080/actuator/health | grep -E 'HTTP/1.1 (200|202)'"]

CMD ["bash", "-c", "java -jar /app.jar"]
