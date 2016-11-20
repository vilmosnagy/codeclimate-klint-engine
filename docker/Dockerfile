FROM openjdk:8-jdk

MAINTAINER Vilmos Nagy <vilmos.nagy@outlook.com>

RUN useradd -u 9000 -r -s /bin/false app

RUN cd /opt/ && \
    git clone https://github.com/vilmosnagy/codeclimate-klint-engine.git && \
    cd codeclimate-klint-engine/source && \
    ./gradlew clean build fatJar -x test && \
    cd ../../ && \
    cp codeclimate-klint-engine/source/build/libs/codeclimate-klint-engine-all-*.jar codeclimate-klint-engine-all.jar && \
    rm -rf codeclimate-klint-engine

WORKDIR /code
USER app
VOLUME /code

CMD ["java", "-jar", "/opt/codeclimate-klint-engine-all.jar"]