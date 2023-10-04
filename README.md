# WebSockets_Java_Server
Java Spring Boot server broadcasting JSON messages to multiple clients using WebSockets.

Development steps:
1. Set up a new Spring Boot project using Spring initializer https://start.spring.io/ and adding the following dependencies: Spring Web, WebSockets and Spring Security.
2. Write the WebSocketConfig.java file.
3. Write the WebSocketController.java with message broadcasting functionality.
4. Write the client with HTML, CSS and JAVASCRIPT.
5. Write the UserCredentialsConfig.java file to add a layer of security to the app using Spring Security.

Project Packages structure:

    wserver
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── ro
    │   │   │       └── space
    │   │   │           └── wserver
    │   │   │               ├── config
    │   │   │               │   ├── UserCredentialsConfig.java
    │   │   │               │   └── WebSocketConfig.java
    │   │   │               ├── controller
    │   │   │               │   └── WebSocketController.java
    │   │   │               ├── ServletInitializr.java
    │   │   │               └── WserverApplication.java
    │   │   ├── resources
    │   │   │   ├── application.properties
    │   │   │   └── static
    │   │   │       ├── index.html
    │   │   │       ├── script.js
    │   │   │       └── style.css
    │   └── test
    ├── .gitignore
    ├── pom.xml
    └── README.md

*To test the application:*
1. Start the server by running the following commands in the wserver main folder
 - mvn clean package
 - java -jar target/wserver-0.0.1-SNAPSHOT.war

The server will print to the console the Unit number everytime a new unit is produced, every 5 seconds.

2. To connect to the server, start a client by opening a browser and typing:
 - localhost:8080/index.html

in the web address bar. A login page will appear, you can use one of the follwoing sets of crdentials:
 - user1 password1
 - user2 password2

Then you will be connected to the server and start receiving the server broadcasts.

You can open multiple clients at the same time, and each client will only receive the messages broadcasted after the connection started, not before.

See image1 for a test example.


*Application Features:*

*2.1. The server broadcasts JSON messages every 5 seconds for producing Units, using the following method:*

    @Scheduled(fixedRate = 5000)  // broadcasting every 5 seconds
    public void broadcastUnit() { 
        unitCount++;
        String message = "{\"message\":\"Unit " + unitCount + "\"}";
        System.out.println("Unit " + unitCount + " produced");
        simpMessagingTemplate.convertAndSend("/topic/units", message);
    }

*2.2. Each clients connects to the WebSocket server and subscribes to the server using:*
    
    stompClient.subscribe('/topic/units', function(message) {...}

The client is written in HTML (indesx.html), CSS (style.css) and JAVASCRIPT (script.js).
The server can support multiple clients at the same time.

*2.3. Each clients receives the broadcasts from the server in real time, in the format of:*

- Server produced Unit n
- Server produced Unit n+1
- Server produced Unit n+2,

where n = the first unit the server produced after the client has connected to the server.
