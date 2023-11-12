#!/bin/bash

# create exchange
curl -i -u guest:guest -H "content-type:application/json" \
    -XPUT -d'{"type":"direct","durable":true}' \
    http://localhost:15672/api/exchanges/%2f/demo.exchange

# create queue
curl -i -u guest:guest -H "content-type:application/json" \
    -XPUT -d'{"durable":true,"arguments":{"x-dead-letter-exchange":"", "x-dead-letter-routing-key": "demo.queue.dead-letter"}}' \
    http://localhost:15672/api/queues/%2f/demo.queue

# create queue related dead letter queue
curl -i -u guest:guest -H "content-type:application/json" \
    -XPUT -d'{"durable":true,"arguments":{}}' \
    http://localhost:15672/api/queues/%2f/demo.queue.dead-letter

# create binding
curl -i -u guest:guest -H "content-type:application/json" \
    -XPOST -d'{"routing_key":"","arguments":{}}' \
    http://localhost:15672/api/bindings/%2f/e/demo.exchange/q/demo.queue