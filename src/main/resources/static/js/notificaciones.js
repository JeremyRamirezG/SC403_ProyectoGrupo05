var stompClient = null;
var privateStompClient = null;

var socket = new SockJS('/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {
    console.log(frame);
    stompClient.subscribe('/all/messages', function(result) {
        show(JSON.parse(result.body));
    });
});

socket = new SockJS('/ws');
privateStompClient = Stomp.over(socket);
privateStompClient.connect({}, function(frame) {
        console.log(frame);
        privateStompClient.subscribe('/user/specific', function(result) {
        console.log(result.body)
            show(JSON.parse(result.body));
        });
    });


function sendMessage() {
    var text = document.getElementById('text').value;
    stompClient.send("/app/application", {},
      JSON.stringify({'text':text}));
}

 function sendPrivateMessage() {
    var text = document.getElementById('privateText').value;
    var to = document.getElementById('to').value;
    stompClient.send("/app/private", {},
      JSON.stringify({'text':text, 'to':to}));
}

function show(message) {
    var response = document.getElementById('messages');
    var p = document.createElement('p');
    p.innerHTML= message.text;
    response.appendChild(p);
}