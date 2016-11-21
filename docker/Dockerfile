FROM openjdk:8-jdk

MAINTAINER Vilmos Nagy <vilmos.nagy@outlook.com>

COPY docker/engine.json /

COPY source/ /opt/source

WORKDIR /opt/source/
RUN ./gradlew clean build fatJar
RUN cp build/libs/codeclimate-klint-engine-all-*.jar /opt/codeclimate-klint-engine-all.jar

# automatic build from github to docker hub
#RUN cd /opt/ && \
#    # git clone https://github.com/vilmosnagy/codeclimate-klint-engine.git && \
#    cd codeclimate-klint-engine/source && \
#    ./gradlew clean build fatJar -x test && \
#    cd ../../ && \
#    cp codeclimate-klint-engine/source/build/libs/codeclimate-klint-engine-all-*.jar codeclimate-klint-engine-all.jar && \
#    rm -rf codeclimate-klint-engine

RUN useradd -u 9000 -r -s /bin/false app
USER app
WORKDIR /code
VOLUME /code

CMD ["java", "-jar", "/opt/codeclimate-klint-engine-all.jar"]
