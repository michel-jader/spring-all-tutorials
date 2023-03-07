# first, enter the kafka docker container
docker exec -it kafka sh

# example to describe topics
kafka-topics --bootstrap-server localhost:9092 --describe


# creating first_topic
kafka-topics --bootstrap-server localhost:9092 --create --topic first_topic


# to issue commands in a remote secured kafka server, its necessary to create a config file and issue 
# a command like the below example:
kafka-topics --command-config my-config-file.config --bootstrap-server my.secure.cluster.com:9092 --create --topic first_topic
