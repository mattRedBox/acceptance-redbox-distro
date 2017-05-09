#!/bin/bash
set -eo pipefail

if [ $# -lt 1 ]; then
	echo "Usage: $0 <command (e.g., up|down|restart)>" && exit 1
fi
export RUN_ARG=$1
export DOCKER_COMPOSE_YAML_PATH=/opt/docker/docker-compose/docker-compose.yml

if [ ! -f ${DOCKER_COMPOSE_YAML_PATH} ]; then
	echo "Unable to find ${DOCKER_COMPOSE_YAML_PATH} Aborting this run..."
	exit 1
fi

echo "Validating HOSTIP variable..."
source /usr/local/bin/create_env_hostip.sh
[ ! -z "${HOSTIP}" ] || exit 1

echo "Running default docker-compose..."
/usr/local/bin/docker-compose -f ${DOCKER_COMPOSE_YAML_PATH} pull
/usr/local/bin/docker-compose -f ${DOCKER_COMPOSE_YAML_PATH} ${RUN_ARG}
