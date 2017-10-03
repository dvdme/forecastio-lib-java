ForecastIO-Lib-Java
===================

- Added the ability to export Currently, Minutely, Hourly and Daily data to CSV. 

 To my knowledge, the number of entries in a certain data block varies. For instance, hourly data may or may not contain the entry `visibility`, and daily data may or may not contain the entry `precipIntensityMaxTime`.

Apparently, dvdme has already noticed it, and that is probably the reason for having a `getFieldsArray` before displaying data. In order to cover all entries, column names are pre-defined in `FIOCsv.java` based on the API provided [here](https://darksky.net/dev/docs/forecast). Non-existing entries will be replaced by `null` when writing to csv file. I have made one modification in `FIODataPoint.java` for the purpose of eliminating `NumberFormatException` when one of the entries involves the keyword **Time** does not exist in the returned JSON, such as `precipIntensityMaxTime`.

Rui Gong