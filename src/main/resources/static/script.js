// Create a new WebSocket connection to the server
var socket = new SockJS('http://localhost:8080/ws');

// Create a Stomp client over the WebSocket connection
var stompClient = Stomp.over(socket);

// Provide authentication headers
var headers = {
    Authorization: 'Basic ' + btoa('user:password')  // Base64 encode the username and password
};

// Connect to the WebSocket server with the provided headers
stompClient.connect(headers, function(frame) {
    console.log('Connected: ' + frame);
    // Subscribe to the '/topic/units' topic on the server
    stompClient.subscribe('/topic/units', function(message) {
        // Parse the received JSON message
        var receivedMessage = JSON.parse(message.body);
        // Log the received message to the console
        console.log('Server produced ' + receivedMessage.message);
        // Write the received message to the HTML page
        document.body.innerHTML += 'Server produced ' + receivedMessage.message + '<br>';
    });
});
