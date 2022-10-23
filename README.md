# Talking clock coding  
clock that present the current time in a more "Human Friendly" way


##Manual ##
Clone the repository to the local.

```angular2html
git clone https://github.com/himasaichand/-clock_coding_challenge_with_rest.git
```

<br>

### Objective  1  &  2 
Write a command-line program that returns the current time using the "Human Friendly Text" demonstrated in the example below.

```angular2html
Option 1 (from command line):

$ mvn package exec:java -Dexec.mainClass=com.hima.Main

Unit test can be executed via:
mvn test -Dsuites=ClockTest
```

```angular2html
Option 2 (From intellij IDE/eclipse):

 1. Run directly from  com/hima/Main.scala to get the current time
 2. For custom time conversiosns, edit class configurations and pass required time as argument to main class(Main.scala) 
 3. For unit tests run ClockTest.scala
```

<br>



### Objective 3 ###
Write a REST service to expose the clock and allow an optional parameter to pass the arbitrary Numeric Time like Objective 2, returning the "Human Friendly Text" .

* Run the following terminal to start the server:

```angular2html
Run TimeRestAppMain.scala which spins up the spring boot server
```
* check the default URL http://localhost:8080/now to get the current time.
* Pass the custom time to the URL in HH:MM or H:MM format like http://localhost:8080/now?time=[specify time] to get the specified time .
  e.g: http://localhost:8080/now?time=11:30


