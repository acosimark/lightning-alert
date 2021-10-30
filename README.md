# lightning-alert

Implemented using Java 8 and Maven 3 and uses [GeoTools](https://github.com/geotools/geotools) for converting <b>longitude</b> and <b>latitude</b> to <b>quadKey</b>


## To build:

<b>Note</b>: Java 8 and Maven 3 <b>must</b> be installed in your local machine

1. Inside the project, open a command line and run the following commands:

```cmd
mvn spring-javaformat:apply clean install
```

## To run:

* Using maven:

```cmd
mvn spring-boot:run
```

* Using java:

```cmd	
cd target/
java -jar lightning-alert-0.0.1-SNAPSHOT.jar
```

### Using the application

Once running, you can use the following commands:
* to know more how to execute the alert command

```cmd
alert help
```

* to execute an alert

```cmd
alert <lightning-event-file-path> <assets-file-path> <optional-zoom-level>
```

sample usage: 

```cmd
alert C:\\dev\\dtnse-lightning-alert-b335f8e92e02\\lightning.json C:\\dev\\dtnse-lightning-alert-b335f8e92e02\\assets.json
```

`Note`: you can also provide a 3rd argument to override the <b>zoom level</b> which currently defaults to 12