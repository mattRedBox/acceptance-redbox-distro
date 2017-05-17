FROM groovy:jdk8
USER root
ENV GRADLE_VERSION=3.5 GRADLE_PARENT=/opt/gradle
WORKDIR ${GRADLE_PARENT}
RUN apt-get update -y
RUN apt-get install -y openssl
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -O gradle-${GRADLE_VERSION}-bin.zip
RUN unzip gradle-${GRADLE_VERSION}-bin.zip
RUN ln -s ${GRADLE_PARENT}/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle
WORKDIR /home/groovy
USER groovy
RUN gradle --version

ENV PROJECT_HOME=/home/groovy/acceptance
WORKDIR $PROJECT_HOME
COPY buildSrc/src ${PROJECT_HOME}/buildSrc/src
COPY buildSrc/build.gradle ${PROJECT_HOME}/buildSrc/
COPY src ${PROJECT_HOME}/src
COPY build.gradle ${PROJECT_HOME}/
COPY gradle/osSpecificDownloads.gradle ${PROJECT_HOME}/gradle/
USER root
RUN chown -R groovy:groovy /home/groovy
USER groovy
RUN gradle clean wrapper
CMD ./gradlew test