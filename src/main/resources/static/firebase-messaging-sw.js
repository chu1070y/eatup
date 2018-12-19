/*importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

// Initialize Firebase
var config = {
  apiKey: "enter api key",
  authDomain: "gorany-df5bd.firebaseapp.com",
  databaseURL: "https://gorany-df5bd.firebaseio.com",
  projectId: "gorany-df5bd",
  storageBucket: "gorany-df5bd.appspot.com",
  messagingSenderId: "551706831448"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function(payload){
	
	const title = "Hello World";
	const options = {
			body: payload.data.status
	};
	
	return self.registration.showNotification(title,options);
});*/