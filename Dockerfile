FROM eclipse-temurin:20-jdk AS build

ARG GRADLE_VERSION=8.2

RUN apt-get update && \
    apt-get install -y --no-install-recommends unzip wget && \
    rm -rf /var/lib/apt/lists/*

RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip gradle-${GRADLE_VERSION}-bin.zip && \
    rm gradle-${GRADLE_VERSION}-bin.zip && \
    mv gradle-${GRADLE_VERSION} /opt/gradle

ENV GRADLE_HOME=/opt/gradle
ENV PATH="${PATH}:${GRADLE_HOME}/bin"

WORKDIR /app

COPY . .

RUN gradle installDist

FROM eclipse-temurin:20-jre

WORKDIR /app

COPY --from=build /app/build/install/app /app

CMD [ "./bin/app" ]