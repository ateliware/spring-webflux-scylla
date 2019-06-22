# spring-webflux-scylla

This is a demo repository using Spring Boot Webflux and Scylla DB, based on https://www.baeldung.com/spring-data-cassandra-reactive

# Create the KEYSPACE at your Scylla DB
CREATE KEYSPACE ateliware
    WITH REPLICATION= {'class':'SimpleStrategy', 'replication_factor' : 1};

    