#!/usr/bin/env bash
set -eo pipefail

## selenium nodes will need to see the website - if this is being hosted on host itself, will need host entry in /etc/hosts
echo "Getting docker host ip for containers..."
export OSNAME=`uname -s`
if [ "${OSNAME}" == "Darwin" ]; then
    export HOSTIP=10.200.10.1 ## or any suitable private ip address
    echo "Creating loopback alias..."
    sudo ifconfig lo0 alias ${HOSTIP}/24
elif [ "${OSNAME}" == "Linux" ]; then
    export HOSTIP=`ip -4 addr show scope global dev docker0 | grep inet | awk '{print $2}' | cut -d/ -f1`
else
    echo "Do not recognise OS: ${OSNAME}. This is needed to set up host entries for containers to reach host website. Exiting..."
    exit 1
fi
echo "Docker host ip is: ${HOSTIP}"