# spring-cloud-consul-test

Test project for setting up communication between microservices using Spring Boot Cloud Consul and Consul.

# How to run

1. Install consul: https://www.consul.io/docs/install/index.html
2. Run consul using command:
``consul agent -server -bootstrap-expect=1 -data-dir=./ -ui -bind=you-ip``
3. Run microservices:
``gradle bootRun``
