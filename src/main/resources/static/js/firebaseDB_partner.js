console.log("firebase realtime db js");

// Initialize Firebase
var config = {
  apiKey: "AIzaSyAKegY8LM_wqmn2twvBsMh0LsvGwsUjS6E",
  authDomain: "gorany-df5bd.firebaseapp.com",
  databaseURL: "https://gorany-df5bd.firebaseio.com",
  projectId: "gorany-df5bd",
  storageBucket: "gorany-df5bd.appspot.com",
  messagingSenderId: "551706831448"
};
firebase.initializeApp(config);

var preObject = document.getElementById('object');

//child안에 sno값을 넣어주면 된다.
var dbRefObject = firebase.database().ref().child(1);

dbRefObject.on('value',snap =>  {

	//preObject.innerText = JSON.stringify(snap.val(),null,3);

	
	for(var data in snap.val()){
		console.log(snap.val()[data]);
		
		var clone = $(".block").clone();
		
		console.log(clone);
		
		clone.attr("class","block");
		
		$("#orderlist").append(clone);
	}

	
});

console.log("firebase realtime db js end");