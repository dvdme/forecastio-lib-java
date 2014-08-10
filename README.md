ForecastIO-Lib-Java
===================

A Java library for the [Forecast.io](//www.forecast.io) API.
It is quite functional at this point. The code is available as an Eclipse project.
A jar file is available under the jar folder for convenience.

## Requirements

* [Java JDK 1.3](//www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase13-419413.html)
* [JSON-lib](//json-lib.sourceforge.net/)
* [JUnit](//junit.org/) (for development only)

## Install

### Install dependencies

Download the JSON-lib library: [download link](//sourceforge.net/projects/json-lib/files/json-lib/json-lib-2.4/)

Download JSON-lib dependencies:

* [Apache Commons BeanUtils](//commons.apache.org/proper/commons-beanutils/download_beanutils.cgi)
* [Apache Commons Collections](//commons.apache.org/proper/commons-collections/download_collections.cgi)
* [Apache Commons Lang](//commons.apache.org/proper/commons-lang/download_lang.cgi)
* [Apache Commons Logging](//commons.apache.org/proper/commons-logging/download_logging.cgi)
* [EZMorph](//sourceforge.net/projects/ezmorph/)

If you wich to contribute to the development, also install [JUnit](http://sourceforge.net/projects/junit/files/junit/3.8.2/).

Install all the downloaded dependencies.

### Install library

Two jars can be used:

```bash
\-- jar
    \-- java-forecastio-lib.jar        # library without sources and javadoc
    \-- java-forecastio-lib-full.jar   # library with sources and javadoc
```

Install the `java-forecastio-lib` of your choice in your project libraries by adding it to your `Build Path`.

### Get your API key

Request an API key [here](//developer.forecast.io/). It is free up to 1000 call per day.

## Documentation

### Project documentation

* Javadoc
* [Forecast.io API doc](//developer.forecast.io/docs/v2)

### Project structure

```bash
\-- src             // application sources
    \-- main        // application classes
    \-- libs        // external libraries
    \-- tests       // tests suites and tests cases
\-- javadoc         // application javadoc
\-- jar             // application jar files

```

## Usages examples

The API is handled by the main class ForecastIO. A ForecastIO instance is associated to an API key and a location.

Simple use:

```java
ForecastIO forecast = new ForecastIO(API_key, 38.7252993, -9.1500364);  // instantiation
forecast.requestForecast();                                             // request forecast for the current location
forecast.getCurrently().getTemperature();                               // get the current temperature
```

You can change the location and set optional parameters later. It is also possible to set them at the instantiation:

```java
ForecastIO forecast = new ForecastIO(API_key, 38.7252993, -9.1500364);                  // instantiate the class
forecast.setLang(FIOLangEnum.EN);                                                       // set the language
forecast.setUnits(FIOUnitsEnum.SI);                                                     // set the units of the API response
forecast.setExclude(new String[] {FIODataBlocksEnum.HOURLY, FIODataBlocksEnum.DAILY})   // exclude unneeded reports from the API response

// example of instantiation with all the parameters at once:
ForecastIO forecast = new ForecastIO(API_key, 38.7252993, -9.1500364, FIOLangEnum.EN, FIOUnitsEnum.SI, new String[] {FIODataBlocksEnum.HOURLY, FIODataBlocksEnum.DAILY});
```

Currently, minutely, hourly, daily and flags data are all accessible from the ForecastIO class:

```java
forecast.getCurrently();
```

Each handler has its way to access to its own data:

```java
forecast.getCurrently().getTemperature();
forecast.getCurrently().getHumidity();
forecast.getCurrently().getPressure();
forecast.getCurrently().getWindSpeed();

forecast.getDaily().getSummary();
forecast.getDaily().getDataPoint(0).getTemperature();
forecast.getDaily().getDataPoint(1).getTemperature();
forecast.getDaily().getDataPoint(2).getTemperature();

forecast.getFlags();
forecast.getAlerts();
```

## Contribution guidelines

### Suggest features

You can suggest features at GitHub :)

### Enhance the API

If you want to add some features, don't hesitate to do so. But don't forget to add the doc and test cases !

To see the project structure, check the **Documentation** paragraph.

## Bug reporting

### Before reporting a bug

#### Bug or feature ?
**bug:** something that should work but does not work, contrary to the developer's intentions.
**feature:** something which software does or would do if somebody coded it.

#### Gather useful information ####
Here is a list of useful information that should be mentioned in your bug report:

* Version of the system being used. Always specify package version. Saying "the latest", "todays", or "the package in extra" have absolutely no meaning. Especially if the bug is not about to get fixed right away.
* Version of the main libraries used by the package, when they are involved in the problem. If you do not know exactly what information to provide then wait for a bug hunter to ask you for it...
* Include relevant informations
* Provide explainations or example to reproduce the bug

#### Opening an issue ####

To report issues please do it in Github or send an mail to **david.dme@gmail.com**.

## Changelogs

#### Update (22-07-2014) (not backward compatible)
* The wrapper is now compatible with Java 1.3
* Better handling and documentation
* Implementation of test cases

#### Update (15-12-2013):
* Alerts are now supported.
* Errors are now supported.
* New properties in DataPoints are now supported.
* The URL is now constructed with StringBulder().
* Improved the internal httpGET method and it should not return truncated responses anymore. ( Suggestion by [DragiPandeliev](https://github.com/DragiPandeliev) )
* The request is now has `Accept-Encoding: gzip` added to the header, acording with the heartly recommendation of [Forecast.io](https://developer.forecast.io/docs/v2) documentation.
* Improved flags class.
* Other code improvements.

#### Update (29-06-2013):
* Return null if the field is not defined rather than -1d where -1 might be an accurate value.( Contribution by [matthew-cox](https://github.com/matthew-cox) ) 

#### Update (27-06-2013):
* Fixed bug in timeURL in the internal url builder. ( Contribution by [matthew-cox](https://github.com/matthew-cox) ) 
* Fixed some typos in the README.md

#### Update (22-06-2013):
* Thanks to a contribution by [brobzilla](http://github.com/brobzilla), ForecastIO-Lib-Java can be used with an external HTTP library. 
  The request URL can be obtained by the `getUrl` method in the `ForecastIO` class.
  The ForecastIO method `getForecast` can now also be called with a `JsonObject` or with a `String` as parameter.
  Check the "Usage Examples" bellow to see how to use an external HTTP library.
* Better error handling while using the internal HTTP method.

## Contributors

* [Theo FIDRY](//github.com/theofidry)
  * Added the Java 1.3 compatibility

* [David Ervideira](//github.com/dvdme) 
  * Initial implementation and main development 

* [Rob Kennedy](//github.com/brobzilla)
  * Add the possibility to use an external http library

## License

The code is available under the terms of the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html).