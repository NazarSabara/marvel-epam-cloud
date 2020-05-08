# marvel-epam-cloud
This is training project based on [Microservice Architecture Pattern](https://martinfowler.com/microservices/) using 
Spring Boot, Spring Cloud and Docker.

marvel-epam-cloud is pretty simple application about PvP/TvT battles between different superheroes.

![github-large](https://user-images.githubusercontent.com/30087741/78811236-348d7400-79d2-11ea-92ec-bfaefaee0a6f.png)

Technology | Version|
------ | --------|
Java | 11|
Spring Boot | 2.2.5|
Spring Cloud | Hoxton.SR3|
Maven | 3.6.3|
docker-compose | 2.1 |

## Core-service
Contains all the heroes supported by this application. Apart from that currently all users also are stored here. When service
starts all Heroes are fetched to the database through Integration-service.

Method	| Path	| Description	| 
------ | ----------- | ------------ |
GET	| /super-heroes/{id}	| Get Hero by id	| 
GET	| /super-heroes	| Get all Heroes	| 
GET	| /users/{id}	| Get User by id	| 
POST	| /users	| Post user	|

## Integration-service
Fetches heroes from [Superhero API](https://superheroapi.com/).

Method	| Path	| Description	| 
------ | ----------- | ------------ |
GET	| /heroes/{id}	| Fetch single Hero from Superhero API	| 
GET	| /heroes| Fetch all Heroes from Superhero API	| 

## Simulation-service
Performs fight simulation. Battle flows based on the Powerstats of Heroes:
  - Intelligence – defines magical damage/defence, one of the primary attributes;
  - Strength – defines magical damage/defence, one of the primary attributes;
  - Speed – identifies who moves first;
  - Durability - specifies hero's health points;
  - Power – defines primary attribute damage multiplier;
  - Combat – defines primary attribute defence.
  
  Method	| Path	| Description	| 
  ------ | ----------- | ------------ |
  POST	| /battle	| Simulates battle	| 
  
## Gateway-service
Consists of two projects:
- Gateway-edge-server;
- Gateway-frontend.
#### Gateway-edge-server
Stores static content (ui application) and routes requests to appropriate microservices using Zuul.
#### Gateway-frontend
ReactJS project.
## Other nodes
#### Config service
Stores configuration files of each service.
#### Discovery-service
Registers services with Eureka Server and provides meta-data, such as host and port, health indicator etc.
## How to run
#### Before you start
- Make sure Docker is running;
- Build project: `mvn package`.

#### Production mode
None
#### Development mode
 You need to clone all repository and build artifacts with maven. Then run `docker-compose up` command.
