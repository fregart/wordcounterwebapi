## wordcounterwebapi
A small word counter web api

### Description

 The program counts frequency of words in a string that is sent using curl.
 It is written in java and using spring boot framework.
 
 ### Instructions
 
 You can import the project into your favorite IDE.
 The main program is called HemtestMain.java.
 
 This program was created in VSCode using Maven and Spring boot so you might
 have to set up your environment to run local server.
 
 If you want to change the server port go to this file: src\main\resources\application.properties
 
 ### Usage
 
 You can use these two, but the /count is the main one
 
 localhost:3000/count
 
 localhost:3000/test
 
 To post words you can use this example
 
 
 curl -H "Content-type: text/plain" -X "POST" -d "Banan Äpple Katt Hund Banan Hund Katt Hund" http://localhost:3000/count
 
 
 You can change these words to anything you like "Banan Äpple Katt Hund Banan Hund Katt Hund".
