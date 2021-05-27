# ForecastIO-Lib-Java
[![build-status](https://img.shields.io/github/workflow/status/eau-claire-energy-cooperative/forecastio-lib-java/Java%20CI?logo=Github)](https://github.com/eau-claire-energy-cooperative/forecastio-lib-java/actions/workflows/maven.yml?query=branch%3Amain)
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg)](https://github.com/RichardLitt/standard-readme)

A Java library for the [darksky.net](https://darksky.net) API (previously forecast.io). The API is fully implemented except for callbacks. Further development will continue. A jar file with the dependencies is available under the jar/ folder for convenience. **This jar file is not updated** Latest version is on maven central.

Developed with Java 1.7

## Background

I started writing this library for two main reasons:

First, I wanted to make a serious open source library that was meant to used by anyone and not just by me for quite sometime. Second, I came across the [darksky.net](https://www.darksky.net) API that I found to be functional with clear and good information. Also, I like the weather and weather data and weather prediction so this is going to be very useful for me to implement my crazy ideas about weather software.

## Install

Install as a  Maven dependency:

```xml
<dependency>
	<groupId>com.github.dvdme</groupId>
	<artifactId>ForecastIOLib</artifactId>
	<version>LATEST</version>
</dependency>
```
* Versions 1.5.1 to 1.6.0 are on maven. Avoid using 1.5.3  and 1.5.4 because it was having issues building on graddle because of a not so well made jar file. I recommend using the latest.
* If anyone wants to check the auto generated javadocs they are here: [forecastiolib.dme.ninja(http://forecastiolib.dme.ninja)

## Usage
To use it add the jar file to your project build path or add the classes from
com.github.dvdme.ForecastIOLib and com.eclipse.json ( [minimal-json](https://github.com/ralfstx/minimal-json) ) or add the dependency to pom:

```xml
<dependency>
  <groupId>com.github.dvdme</groupId>
  <artifactId>ForecastIOLib</artifactId>
  <version>1.5.1</version>
</dependency>
```

Data is initialized and fetched by the ForecastIO class:

```java
ForecastIO fio = new ForecastIO(your_api_key); //instantiate the class with the API key.
fio.setUnits(ForecastIO.UNITS_SI);             //sets the units as SI - optional
fio.setExcludeURL("hourly,minutely");             //excluded the minutely and hourly reports from the reply
fio.getForecast("38.7252993", "-9.1500364");   //sets the latitude and longitude - not optional
                                               //it will fail to get forecast if it is not set
                                               //this method should be called after the options were set
```

...or using an external http library:

```java
ForecastIO fio = new ForecastIO(your_api_key); //instantiate the class with the API key.
fio.setUnits(ForecastIO.UNITS_SI);             //sets the units as SI - optional
fio.setExclude("hourly,minutely");             //excluded the minutely and hourly reports from the reply
String response = Some_External_Http_Library.GET(fio.getUrl("38.7252993", "-9.1500364")); //use the getUrl method to access the
                                                                                          //generated request URL.
//Parse the reply
//Option 1, (easiest):
fio.getForecast(response);
//Option 2
fio.getForecast(JsonObject.readFrom(response));
```

If a proxy has to be used:
```java
ForecastIO fio = new ForecastIO(your_api_key); //instantiate the class with the API key.
fio.setHTTPProxy(proxyhostname, proxyport);    //tell, which proxy to use
```
If proxyhostname equals the NullPointer, no proxy will be used.

Currently, minutely, hourly, daily and flags classes are all initialized in the same way,
with a ForecastIO class as an argument:

```java
FIOMinutely minutely = new FIOMinutely(fio);
```

Most data is accessed like this:

```java
currently.get().temperature(); //gets the temperature data for the currently report
daily.getDay(3).humidity();       //gets the humidity data for day 4 in the daily report
```

The following examples print the data available in each class.

Forecast common data:
```java
    ForecastIO fio = new ForecastIO(your_api_key);
	fio.setUnits(ForecastIO.UNITS_SI);
	fio.setLang(ForecastIO.LANG_ENGLISH);
	fio.getForecast("38.7252993", "-9.1500364");
	System.out.println("Latitude: "+fio.getLatitude());
	System.out.println("Longitude: "+fio.getLongitude());
	System.out.println("Timezone: "+fio.getTimezone());
	System.out.println("Offset: "+fio.getOffset());
```
Currently report:
```java
    FIOCurrently currently = new FIOCurrently(fio);
    //Print currently data
	System.out.println("\nCurrently\n");
	String [] f  = currently.get().getFieldsArray();
	for(int i = 0; i<f.length;i++)
		System.out.println(f[i]+": "+currently.get().getByKey(f[i]));
```
Minutely report:
```java
    FIOMinutely minutely = new FIOMinutely(fio);
    //In case there is no minutely data available
	if(minutely.minutes()<0)
		System.out.println("No minutely data.");
	else
		System.out.println("\nMinutely\n");
	//Print minutely data
	for(int i = 0; i<minutely.minutes(); i++){
		String [] m = minutely.getMinute(i).getFieldsArray();
		System.out.println("Minute #"+(i+1));
		for(int j=0; j<m.length; j++)
			System.out.println(m[j]+": "+minutely.getMinute(i).getByKey(m[j]));
	}
```
Hourly report:
```java
    FIOHourly hourly = new FIOHourly(fio);
    //In case there is no hourly data available
	if(hourly.hours()<0)
		System.out.println("No hourly data.");
	else
		System.out.println("\nHourly:\n");
	//Print hourly data
	for(int i = 0; i<hourly.hours(); i++){
		String [] h = hourly.getHour(i).getFieldsArray();
		System.out.println("Hour #"+(i+1));
		for(int j=0; j<h.length; j++)
			System.out.println(h[j]+": "+hourly.getHour(i).getByKey(h[j]));
		System.out.println("\n");
	}
```
Daily report:
```java
    FIODaily daily = new FIODaily(fio);
    //In case there is no daily data available
	if(daily.days()<0)
		System.out.println("No daily data.");
	else
		System.out.println("\nDaily:\n");
	//Print daily data
	for(int i = 0; i<daily.days(); i++){
		String [] h = daily.getDay(i).getFieldsArray();
		System.out.println("Day #"+(i+1));
		for(int j=0; j<h.length; j++)
			System.out.println(h[j]+": "+daily.getDay(i).getByKey(h[j]));
		System.out.println("\n");
	}
```
Flag report:
```java
    FIOFlags flags = new FIOFlags(fio);
    //Print information for metar stations
	for(int i=0; i<flags.metarStations().length; i++)
		System.out.println("Metar Stations: "+flags.metarStations()[i]);
	System.out.println("\n");
	//Print all available flags
	for(int i=0; i<flags.availableFlags().length; i++)
		System.out.println(flags.availableFlags()[i]);
```
Alerts report:
```java
    FIOAlerts alerts = new FIOAlerts(fio);
	//Check if there are alerts
	if(alerts.NumberOfAlerts() <= 0){
		System.out.println("No alerts for this location.");
	}
	//if there are alerts, print them.
	else {
		System.out.println("Alerts");
		for(int i=0; i<alerts.NumberOfAlerts(); i++)
			System.out.println(alerts.getAlert(i));
	}
```

### What is does:
* It can read Data Points and Data blocks from the [Forecast.io](http://www.forecast.io) API.
  * This means it can read Currently, Minutely, Hourly and Daily data.
* It reads all available fields.
* It reads all the available flags ~~except one - `metno-license`.~~
* It reads all the available alerts.
* It reads all the available errors.

### What it does not:
* ~~It does not read alerts and errors (the confidence in the prediction provided by the API).~~ Already implemented.
* It does not implements the `callback` request option. Did not seamed relevant for this.

### To Do:
* ~~Improve time zone support~~ Kind of done.
* ~~Add support to errors (confidence in prediction)~~ Done.
* ~~Add support to alerts~~ Done.
* (maybe) Add the ability to export data to CSV
* (maybe) Add the ability of converting units of received data:
      (This would make sense if there were the need of displaying data in various units without having to make multiple queries.)


## Documentation

I generated a javadoc based in the comments I made. It is included in the files under the javadoc/ folder and here [forecastiolib.dme.ninja](http://forecastiolib.dme.ninja) but do not expect it to be best documentation ever.

## Acknowledgements

Thanks to JetBrains for providing an open source license for [IntelliJ IDEA](https://www.jetbrains.com/idea/).

[DarkSKY](https://darksky.net/poweredby/)

## Contributing

Create PR if you have something to add or fix! 

Thanks to everyone that [contributed](https://github.com/dvdme/forecastio-lib-java/graphs/contributors) to make this software better.

## License

The code is available under the terms of the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html).

