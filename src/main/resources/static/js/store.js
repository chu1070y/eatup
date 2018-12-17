//가장 바깥에 위치한 함수. 음식점 추가&삭제 기능 담당
// 즉시 실행 함수로 만들어 return한 애가 dao가 되도록 함. dao는 객체가 됨. dao 스스로가 객체가 되는, 객체지향 코딩.
// dao는 실제로 외부 파일로 빼서 할 수 있음.
var dao = (function (map){

    var map = map;
    var arr = new Array();
	
	//가게 리스트 불러오기
	getStore();
    
	function getStore(callback,error){
		console.log("getStore...............");
		
		$.getJSON("/storeList", function(data){
            
			var storeList = new Array();;
			
			$.each(data, function(i, item){
				arr[i] = {title: item.sname, lat: item.lat, lng:item.lng};
			}); //end for
		});//end getJson
	};//end getStore
		
    //마커 지우는 역할
    function clearMarkers(){
        for(var store of arr){
            if(store.marker){
                store.marker.setMap(null);
            }
        }
    }

    function clone(origin){
        var result = [];
        for(var obj of origin){
            result.push(obj);
        }
        return result;
    }

    //거리 계산하기 추가. current는 현재 위치(지도 이동에 따라 변화한 위치). > 이동했을 때 가장 가까운 애만 반환시키는 함수.
    //arr을 그대로 쓰면 idx가 섞이므로 arr을 copy해서 쓰는 게 좋음.
    function findNNStore(current){
        function calcDistance(p1, p2){
            return Math.sqrt(Math.pow(p1.lat - p2.lat, 2) + Math.pow(p1.lng - p2.lng, 2));
        }
        var target = clone(arr);
        target.sort(function(a, b){
            var d1 = calcDistance(current, a) * 1000000;   //값이 작으므로 10만 곱해줌
            var d2 = calcDistance(current, b) * 1000000;
            return d1 - d2;
        });
        console.log(target);
        return target;
    }

    //listUL에 목록을 만드는 작업을 수행? 파라미터로 함수를 전달할 것(listUL에 목록을 전달하는 함수). 콜백패턴(특정 일이 다 이루어질 때 작업해줫!)
    function getList(callback) {
        callback(arr);
    }
    
    //마커 보여주는 기능. 파라미터로는 idx 번호
    //음식점에 마커 주고, 마커가 있으면 더이상 추가되지 않도록 만들기
    function showMarker(idx){

        clearMarkers();

        var store = arr[idx];
        if(!store.marker){
            store.marker = new daum.maps.Marker({
                position: new daum.maps.LatLng(store.lat, store.lng),
                map: map
            });
        }else{
            store.marker.setMap(map);
        }
        if(!store.infoWin){
            var infoWin = new daum.maps.InfoWindow({
                content: store.title // 인포윈도우에 표시할 내용
            });
        }
    }

    //외부에 노출시켜야 하는 것들을 return (흠)
    //return 시 키:값, 키:값,
    return {
        getList:getList,
        showMarker:showMarker,
        findNNStore:findNNStore
    }

})(map);