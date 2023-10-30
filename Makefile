.PHONY: start stop

start:
	docker compose -f docker-compose.yml up -d
	# TODO: configure mq queue (create, bind)

stop:
	docker compose -f docker-compose.yml down --remove-orphans