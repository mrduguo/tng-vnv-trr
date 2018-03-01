[![Build Status](http://jenkins.sonata-nfv.eu/buildStatus/icon?job=tng-vnv-trr/master)](https://jenkins.sonata-nfv.eu/job/tng-vnv-trr)

# Test Result Repository for 5GTANGO Verification and Validation
This is a [5GTANGO](http://www.5gtango.eu) component to coordinate the verification and validation activities of 5G Network Services.


## Build from source code

```bash
./gradlew
```

The project depends on java and docker to build. Once you have those two tools, you simply run `./gradlew` command without parameter to do a full build:
* clean : clean the project build directory
* compile code
* process resources
* package jar
* compile test
* process test resources
* execute test
* execute docker build
* execute docker push: optional, default to
  * `true` on jenkins build
  * `false` on local build

For advanced build arguments, please checkout the [gradle-buildscript](https://github.com/mrduguo/gradle-buildscript) project.


## Run mandatory dependency mongo db

```bash
docker run -d --name vnv-mongo -p 27017:27017 mongo:3.6.2

docker rm -f vnv-mongo
docker run -d --name vnv-mongo mongo:3.6.2

docker run -it --rm --link vnv-mongo:mongo mongo:3.6.2 mongo  admin some-mongo/some-db

docker exec -it vnv-mongo mongo 

use foo
db.testData.insert({ Name : "TecAdmin.net" })
db.testData.find()
db.getName()
show collections
show dbs
```

## Run the docker image

```bash
docker pull registry.sonata-nfv.eu:5000/tng-vnv-trr
docker run -d --name tng-vnv-trr -p 6300:6300 registry.sonata-nfv.eu:5000/tng-vnv-trr
```

### Health checking

Once the component finish start, you can access (change IP depends on your docker setup) the component health endpoint at:

http://192.168.99.100:6300/tng-vnv-trr/health

### Swagger UI

http://192.168.99.100:6300/tng-vnv-trr/swagger-ui.html


## License

This 5GTANGO component is published under Apache 2.0 license. Please see the [LICENSE](LICENSE) file for more details.

## Lead Developers

The following lead developers are responsible for this repository and have admin rights. They can, for example, merge pull requests.

* Guo Du ([mrduguo](https://github.com/mrduguo))

