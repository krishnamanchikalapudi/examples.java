'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        // message.content =  message.sender + ' joined!';
        message.content =  'Welcome '+ message.sender + ', How can I help you today?';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender );
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    
    // TODO: BOT msg append
    if (message.addressbook != undefined && message.addressbook != null  ) {
    	 
    	messageElement.classList.add('chat-message');
    	
        var botAvatarElement = document.createElement('i');
        var botAvatarText = document.createTextNode('CB');
        botAvatarElement.appendChild(botAvatarText);
        botAvatarElement.style['background-color'] = getAvatarColor('CB');
        messageElement.appendChild(botAvatarElement);

        var botUserElement = document.createElement('span');
        // var botUserText = document.createTextNode('CHAT BOT'+ getCurrentDateTime());
        var botUserText = document.createTextNode('CHAT BOT');
        botUserElement.appendChild(botUserText);
        messageElement.appendChild(botUserElement)
   
	    var botTextElement = document.createElement('p'); 
    	
        	// name
        var botMessageText1 = document.createTextNode(message.addressbook.name);
    	botTextElement.appendChild(botMessageText1);	
    	var brElement1 = document.createElement('br');
    	botTextElement.appendChild(brElement1);
    		// emailid
    	 var botMessageText2 = document.createTextNode(message.addressbook.email);
     	botTextElement.appendChild(botMessageText2);	
     	var brElement2 = document.createElement('br');
     	botTextElement.appendChild(brElement2);

    	messageElement.appendChild(botTextElement);
	    messageArea.appendChild(messageElement);
    } // if (message.botResponse!== undefined

    messageArea.scrollTop = messageArea.scrollHeight;
}

function getCurrentDateTime() {
	var today = new Date();
	var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds() +  ":" + today.getMilliseconds();
	return (' --> '+ date+' '+time);
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
