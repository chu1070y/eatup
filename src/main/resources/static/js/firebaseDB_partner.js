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
		//$("#btn").attr("id",snap.val()[data].order_id);
		
		for(var menu of snap.val()[data].menulist){
			$("#origin #body").append("<h2>" + menu.menu + "---"+menu.quantity+"</h2><br/>");
		}
		
		
		$("#origin #tid").val(snap.val()[data].tid);
		
		$("#origin #body").append("<button id="+snap.val()[data].order_id+" class='btn btn-secondary btn-lg btn-block'>F I N I S H !</button>");
		
		
		var clone = $("#origin").clone();
		
		clone.attr("class","block");
		clone.attr("id",snap.val()[data].order_id);
		
		$("#orderlist").append(clone);
		$("#origin #body").html("");
		
		//var id = "#"+snap.val()[data].order_id;
		//$(id).attr("id","btn");
		
		
		
	}
	//animation
	$(".btn.btn-secondary.btn-lg.btn-block").click(function(){
		
		
		var number = this.id;
		console.log("클릭"+number);
		
		var array = $(".btn.btn-secondary.btn-lg.btn-block").slice(); //블록을 배열로
		console.log(array);
		
	
		var idarray = new Array(); //배열 아이디
		var length = array.length; 
		console.log(length)
		
		for(var i=0; i<length; i++){
			idarray[i]=array[i].id;
			
		}
		
		console.log("idarray: " + idarray)
	 
		//animation
		var elem = document.getElementById(number); //ex)103
		var indexnumber=parseInt(number)+1
		console.log(indexnumber)
	  	var index = idarray.indexOf(""+indexnumber); 
		console.log(elem + "   " + index)
		console.log("여기 아이디 어레이" + idarray)
	
		$(elem).animate({"bottom":"1000px"});		
		$(".block").slice(index).animate({"left": "-=420px"});  //103숫자 들어오면,,,
		
		
		

		
		
		
		
		
		
		
		
		
	
		
		
		
	});//$(".btn.btn-secondary.btn-lg.btn-block").click

	
});

console.log("firebase realtime db js end");