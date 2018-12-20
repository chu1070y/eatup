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

	$("#orderlist").html("");
	//preObject.innerText = JSON.stringify(snap.val(),null,3);

	var clone = $(".block").clone();
	
	for(var data in snap.val()){
		console.log("-----");
		console.log(snap.val()[data]);
		
		$("#origin #header").html("<h1>"+snap.val()[data].order_id+"</h1>");
		
		for(var menu of snap.val()[data].menulist){
			$("#origin #body").append("<h2>" + menu.menu + "---"+menu.quantity+"</h2><br/>");
		}
		
		$("#origin #tid").val(snap.val()[data].tid);
		
		var clone = $("#origin").clone();
		
		clone.attr("class","block");
		clone.attr("id",snap.val()[data].order_id);
		
		$("#orderlist").append(clone);
		$("#origin #body").html("");

	}

	
});

console.log("firebase realtime db js end");