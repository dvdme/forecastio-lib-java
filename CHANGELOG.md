## Update (11-10-2016):
* Changed api url from api.forecast.io to api.darksky.net

## Update (16-01-2016):
* Added proxy support

## Update (12-03-2015):
* Bug fix: No longer crashes with half hour timezones.
* Code improvements.

## Update (07-10-2014):
* I converted the project to maven and it is now available in the repositories. I had to rename the package so now is com.github.dvdme.ForecastIOLib.
* setUnits() and setLang() methods were simplified.
* New getRawResponse() method to return the raw JSON response.

## Update (27-09-2014):
* Response headers that return api calls already made for a given api key and response time are read and available in a get method.
* Javadocs slightly improved (again).

## Update (26-09-2014):
* Supported languages updated.
* Javadocs slightly improved.
* Flag `metno-license` supported.

## Update (23-07-2014):
* The language option is now supported. [Forecast.io](http://www.forecast.io) is available now in english, german, dutch, french, spanish and tetum. If some other is selected, english will be set.
Please note that in `FIOLibTest.java` some messages are hardcoded in english, this has nothing to do with the language option.
(Also, the examples are update with the new option).

## Update (15-12-2013):
* Alerts are now supported.
* Errors are now supported.
* New properties in DataPoints are now supported.
* The URL is now constructed with StringBulder().
* Improved the internal httpGET method and it should not return truncated responses anymore. ( Suggestion by [DragiPandeliev](https://github.com/DragiPandeliev) )
* The request is now has `Accept-Encoding: gzip` added to the header, acording with the heartly recommendation of the [Forecast.io](https://developer.forecast.io/docs/v2) documentation.
* Improved flags class.
* Other code improvements.

## Update (29-06-2013):
* Return null if the field is not defined rather than -1d where -1 might be an accurate value.( Contribution by [matthew-cox](https://github.com/matthew-cox) )

## Update (27-06-2013):
* Fixed bug in timeURL in the internal url builder. ( Contribution by [matthew-cox](https://github.com/matthew-cox) )
* Fixed some typos in the README.md

## Update (22-06-2013):
* Thanks to a contribution by [brobzilla](http://github.com/brobzilla), ForecastIO-Lib-Java can be used with an external HTTP library.
  The request URL can be obtained by the `getUrl` method in the `ForecastIO` class.
  The ForecastIO method `getForecast` can now also be called with a `JsonObject` or with a `String` as parameter.
  Check the "Usage Examples" bellow to see how to use an external HTTP library.
* Better error handling while using the internal HTTP method.