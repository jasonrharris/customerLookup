# Customer Lookup
This is a Demo App that uses Dropwizard to implement a basic REST based Customer Search Application. Data is persisted in a file based H2 instance in the user's home directory (so `~/customers.mv.db`)

To build it, execute `gradle shadowJar` at the root of the project and to launch it, execute `java -jar build/libs/demo-customer-lookup-rest-stub.jar server build/resources/main/config.yml`. This will run a Jetty server, with th service request at url http://localhost:8080/customer/search. To find matching surnames, use (for example) http://localhost:8080/customer/search?surname=Jones.

Three test Customers have been added to the single table backing the Application, so searches for Jones or Patel should return results. No facility has been provided to add data but either an H2 Console could be launched (which would required the `confi.yml` file to be modified as required) or code in the `CustomerLookupApplication` class could be modified (the DAO has an `insert` method).
