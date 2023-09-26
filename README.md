# WebSockets_Java_Server
Java Spring Boot server broadcasting JSON messages to multiple clients using WebSockets.


Application Features:

2.1. The server broadcasts JSON messages every 5 seconds for producing Units, using the following method:
@Scheduled(fixedRate = 5000)  // broadcasting every 5 seconds
    public void broadcastUnit() { 
        unitCount++;
        String message = "{\"message\":\"Unit " + unitCount + "\"}";
        System.out.println("Unit " + unitCount + " produced");
        simpMessagingTemplate.convertAndSend("/topic/units", message);
    }

2.2. Each clients connects to the WebSocket server and subscribes to the server using:
stompClient.subscribe('/topic/units', function(message) {...}

The client is written in HTML (indesx.html), CSS (style.css) and JAVASCRIPT (script.js).
The server can support multiple clients at the same time.

2.3. Each clients receives the broadcasts from the server in real time, in the format of:
Server produced Unit n
Server produced Unit n+1
Server produced Unit n+2,

where n = the first unit the server produced after the client has connected to the server.

2.5. Security layer
Selected option: WebSocket Authentication with Spring Security. The application requires WebSocket clients to authenticate using the login credentials written in the UserCredentialsConfig.java file, so it will only accept WebSocket connections from authenticated clients.

2.4. Scalability
Before implemnenting the security layer:
- any new client could easily join by accessing the localhost:8080/index.html address, so the application was greatly scalable (but not secure).
- after implementing Spring Security (and user login credentials), new user crdentials can be manually added in the UserCredentialsConfig.java class, by writing the following lines:

        // Define user1 with username "user1" and password "password1"
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("password1"))
                .roles("USER")
                .build();

This is not as scalable as before, and a better approach would have been to use a Database, but for the purpose of the application (being able to add a few extra clients easily, not 100+), it is still easy to implement without increasing the complexity of the app.


To test the application:
1. Start the server by running the following commands in the wserver main folder
 mvn clean package
 java -jar wserver-0.0.1-SNAPSHOT.war

The server will print to the console the Unit number everytime a new unit is produced, every 5 seconds.

3. To connect to the server, start a client by opening a browser and typing: localhost:8080/index.html  in the web address bar. A login page will appear, you can use one of the follwoing sets of crdentials:
 user1 password1
 user2 password2

Then you will be connected to the server and start receiving the server broadcasts.
You can open multiple clients at the same time, and each client will only receive the messages broadcasted after the connection started, not before.

See image1 for a test examples.

  
