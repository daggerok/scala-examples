FROM openjdk:8u151-jre-alpine
MAINTAINER Maksim Kostromin https://github.com/daggerok
RUN apk --no-cache add busybox-suid bash curl sudo \
 && adduser -h /home/appuser -s /bin/bash -D -u 1025 appuser wheel \
 && echo "appuser ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers \
 && sed -i "s/.*requiretty$/Defaults !requiretty/" /etc/sudoers \
 && apk del busybox-suid \
 && rm -rf /tmp/* /var/cache/apk/*
USER appuser
WORKDIR /home/appuser
VOLUME /home/appuser
CMD /bin/bash
ENTRYPOINT /bin/bash /home/appuser/app/bin/first-akka-blood
COPY --chown=appuser ./build/install/first-akka-blood app
