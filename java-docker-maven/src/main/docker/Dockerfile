

FROM openjdk:8u181-jre-stretch

LABEL       maintainer="training@acme"

ARG         APP_NAME
ARG         APP_VERSION

ENV         APP_NAME       $APP_NAME
ENV         APP_VERSION    $APP_VERSION
ENV         REMOTE_DEBUG   0

EXPOSE      8080
EXPOSE      8787

RUN         mkdir -p /app

ADD         entry-point.sh /app/
ADD         maven/*.jar /app/

USER root

RUN          chmod +x /app/entry-point.sh \
             && echo "Europe/Berlin" > /etc/timezone

RUN          adduser --system \
                     --no-create-home \
                     --disabled-password \
                     --disabled-login \
                     --shell /bin/false \
                     app \
             && chown -R app /app \
             && chmod -R "g+rwX" /app

USER app

HEALTHCHECK --start-period=10s \
            --interval=10s \
            CMD curl --fail http://localhost:8080/actuator/health || exit 1

CMD         ["sh","/app/entry-point.sh"]