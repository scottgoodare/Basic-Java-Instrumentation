# Basic-Java-Instrumentation

Start of a basic instrumentation agent that will provide timings on specific methods in a JVM. Will push the data into Splunk using the current Splunk API.

Borrows heavily from Damien Dallimore's SplunkJavaAgent (well, it will), utilizing the SplunkLogEvent and some of the transport stuff to get the data into Splunk. Key differences will be

* Splunk API to connect to Splunk and send data
* The agent will pull configuration details from Splunk via an App

