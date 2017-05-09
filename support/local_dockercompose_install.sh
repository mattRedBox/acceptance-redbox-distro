#!/usr/bin/env bash
set -eo pipefail

if [ `whoami` != 'root' ];
        then echo "This script must be executed as root" && exit 1;
fi

echo "Initialising..."
export WORKING_DIR=`cd $(dirname "$0") && pwd`
export DOCKER_COMPOSE_DIR=/opt/docker/docker-compose
chmod +x ${WORKING_DIR}/*.sh

echo "Creating docker compose directory..."
mkdir -p ${DOCKER_COMPOSE_DIR} || exit 1

echo "Copying files..."
cp ${WORKING_DIR}/docker-compose.yml ${DOCKER_COMPOSE_DIR}/
cp ${WORKING_DIR}/*.sh /usr/local/bin/