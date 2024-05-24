const socket = new SockJS('/api/v1/vote');
stompClient = Stomp.over(socket);

function connect() {

  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topics/vote1', function (message) {
      showMessage(JSON.parse(message.body));
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}

function showMessage(message) {
  const messageArea = document.getElementById('messages');
  const p = document.createElement('p');
  p.textContent = message;
  messageArea.appendChild(p);
}