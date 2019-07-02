#!/bin/bash

TEMP_FOLDER="temp-protobuf"
PROTOBUF_VERSION="2.5.0"

# creating dirs
mkdir -p $TEMP_FOLDER

# download zulu jdk
wget https://github.com/protocolbuffers/protobuf/releases/download/v2.5.0/protobuf-$PROTOBUF_VERSION.tar.gz \
  -O $TEMP_FOLDER/protobuf-$PROTOBUF_VERSION.tar.gz

# Untar
tar xvf $TEMP_FOLDER/protobuf-$PROTOBUF_VERSION.tar.gz -C $TEMP_FOLDER

# change dir
cd $TEMP_FOLDER/protobuf-$PROTOBUF_VERSION

# autogen
./autogen.sh

# configure
./configure --prefix=/usr

# make and install
make && make install

# check installation
if which protoc &>/dev/null; then
  exit 1
fi

# remove dir
rm -rf $TEMP_FOLDER
