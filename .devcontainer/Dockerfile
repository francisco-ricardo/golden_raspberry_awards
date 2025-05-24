FROM eclipse-temurin:21

RUN apt update -y && apt install -y \
    build-essential \
    maven \
    vim \
    && rm -Rf /var/lib/apt/lists/*

ENTRYPOINT ["/app/.devcontainer/docker-entrypoint-dev.sh"]
CMD ["/app/.devcontainer/docker-cmd-script-dev.sh"]

# EOF



