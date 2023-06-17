FROM itzg/minecraft-server:latest AS development_build

SHELL ["/bin/bash", "-eo", "pipefail", "-c"]

COPY ./docker/entrypoint.sh /docker-entrypoint.sh
RUN chmod +x '/docker-entrypoint.sh'

ENTRYPOINT ["/docker-entrypoint.sh"]
