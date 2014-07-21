ForecastIO-Lib-Java
===================
A Java library for the [Forecast.io](http://www.forecast.io) API.
It is quite functional at this point.
It still does not fully implements the API but it handles most 
of the data and I intended to continue further development.
Code is available as an Eclipse project.
A jar file is available under the jar folder for convenience.

Java 1.7<br>
Tested under Windows 7 64bits, Linux Mint 15 and Ubuntu 13.04 64bits, but it should run everywhere.

####Update (15-12-2013):
* Alerts are now supported.
* Errors are now supported.
* New properties in DataPoints are now supported.
* The URL is now constructed with StringBulder().
* Improved the internal httpGET method and it should not return truncated responses anymore. ( Suggestion by [DragiPandeliev](https://github.com/DragiPandeliev) )
* The request is now has `Accept-Encoding: gzip` added to the header, acording with the heartly recommendation of [Forecast.io](https://developer.forecast.io/docs/v2) documentation.
* Improved flags class.
* Other code improvements.

####Update (29-06-2013):
* Return null if the field is not defined rather than -1d where -1 might be an accurate value.( Contribution by [matthew-cox](https://github.com/matthew-cox) ) 

####Update (27-06-2013):
* Fixed bug in timeURL in the internal url builder. ( Contribution by [matthew-cox](https://github.com/matthew-cox) ) 
* Fixed some typos in the README.md

####Update (22-06-2013):
* Thanks to a contribution by [brobzilla](http://github.com/brobzilla), ForecastIO-Lib-Java can be used with an external HTTP library. 
  The request URL can be obtained by the `getUrl` method in the `ForecastIO` class.
  The ForecastIO method `getForecast` can now also be called with a `JsonObject` or with a `String` as parameter.
  Check the "Usage Examples" bellow to see how to use an external HTTP library.
* Better error handling while using the internal HTTP method.

####What is does:
* It can read Data Points and Data blocks from the [Forecast.io](http://www.forecast.io) API.
  * This means it can read Currently, Minutely, Hourly and Daily data.
* It reads all available fields.
* It reads all the available flags except one - `metno-license`. 
* It reads all the available alerts. 
* It reads all the available errors. 

####What it does not:
* ~~It does not read alerts and errors (the confidence in the prediction provided by the API).~~ Already implemented.
* It does not implements the `callback` request option. Did not seamed relevant for this.

####To Do:
* ~~Improve time zone support~~ Kind of done.
* ~~Add support to errors (confidence in prediction)~~ Done.
* ~~Add support to alerts~~ Done.
* (maybe) Add the ability to export data to CSV
* (maybe) Add the ability of converting units of received data: 
      (This would make sense if there were the need of displaying data in various units without having to make multiple queries.)

####How it works:
The ForecastIO-Lib-Java currently has 9 classes (I'll probably add two more to deal with errors).
The main class is `ForecastIO`: It handles the connection the gets the initial data from the API.
The classes `FIOCurrently`, `FIOMinutely`, `FIOHourly`, `FIODaily`, `FIOFlags` and `FIOAlerts` 
contain the currently, minutely, hourly, daily, flags and alerts reports.
The classes `FIODataPoint`, `FIODataBlock` handle the data in the previous reports 
(except for the flags). Most of the work is done by the `FIODataPoint` class.

Please refer to the API docs [https://developer.forecast.io](https://developer.forecast.io) 
for better understanding of the data and for the APY key. - You'll need a key to get it to work.

####External Libraries: 

* **minimal-json**
ForecastIO-Lib-Java uses the [minimal-json](https://github.com/ralfstx/minimal-json) for 
parsing the Json API response. I find this library to be great...
This in not a dependency because I added the classes to my project.
[https://github.com/ralfstx/minimal-json](https://github.com/ralfstx/minimal-json<br>
[http://eclipsesource.com/blogs/2013/04/18/minimal-json-parser-for-java/](http://eclipsesource.com/blogs/2013/04/18/minimal-json-parser-for-java/)

######About the package name
In case someone wonders, `dme` are just my initials.
As there is no TLD `.dme` I decided to use them for the package.

Usage Examples
--------------
To use it add the jar file to your project build path or add the classes from
dme.forecastiolib and com.eclipse.json ( [minimal-json](https://github.com/ralfstx/minimal-json) )

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
		System.out.println("No alerts for this locatoin.");
	} 
	//if there are alerts, print them.
	else {
		System.out.println("Alerts");
		for(int i=0; i<alerts.NumberOfAlerts(); i++)
			System.out.println(alerts.getAlert(i));
	}
```

Issues
------
To report issues please do it in [Github](https://github.com/dvdme/forecastio-lib-java) or
send me an <a href="mailto:david.dme@gmail.com">email</a>.<br>

Documentation
-------------
I generated a javadoc based in the comments I made.
It is included in the files under the javadoc/ folder but
do not expect it to be best documentation ever.

History
-------
I started writing this library for two main reasons: 
First, I wanted to make a serious open source library that was meant 
to used by anyone and not just by me for quite sometime.
Second, I came across the [Forecast.io](http://www.forecast.io) API that I found to be functional
with clear and good information.
Also, I like the weather and weather data and weather prediction so this
is going to be very useful for me to implement my crazy ideas about
weather software.

Contributors
------------
* [David Ervideira](http://github.com/dvdme) 
  * Initial implementation and main development 

* [Rob Kennedy](http://github.com/brobzilla)
  * Add the possibility to use an external http library

License
-------
The code is available under the terms of the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html).

Last Update
-----------
15-12-2013
