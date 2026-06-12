### Instructions

To dockerize app run "docker build -t kaiseldor:product-management-system-product-service ."

To run the service via the generated jar you need to:

- Start mongodb with "docker run -p 27017:27017 --name=products_db mongo:latest --replSet rs0"
- Set mongodb instance as primary node with "docker exec -it products_db mongosh --eval "rs.initiate()""
