//가장 바깥에 위치한 함수. 음식점 추가&삭제 기능 담당
// 즉시 실행 함수로 만들어 return한 애가 dao가 되도록 함. dao는 객체가 됨. dao 스스로가 객체가 되는, 객체지향 코딩.
// dao는 실제로 외부 파일로 빼서 할 수 있음.
var dao = (function (map){

    var map = map;
    var arr = [
        {title:'고기팩토리',lat:37.569820, lng: 126.986001},
        {title:'갓덴스시',lat:37.569081,lng:126.985591},
        {title:'코다차야',lat:37.569949,lng:126.985049},
        {title:'롯데리아',lat:37.569857,lng:126.984923},
        {title:'초밥의신부타',lat:37.569440,lng:126.987367},
        {title:'청진식당',lat:37.570515,lng:126.985710},
        {title:'에머이',lat:37.570031,lng:126.984601},
        {title:'쿠킨스테이크',lat:37.569934,lng:126.985787},
        {title:'서브웨이',lat:37.570325,lng:126.985932},
        {title:'미스터시래기',lat:37.573029,lng:126.985420},
        {title: '종로설렁탕', lat:37.570130,lng:126.989828},
        {title: '휘황', lat:37.570198,lng:126.990204},
        {title: '미다래', lat:37.570836,lng:126.991599},
        {title: '한일식당', lat:37.570028,lng:126.991159},
        {title: '시민식당', lat:37.570921,lng:126.990375},
        {title: '새마을식당', lat:37.569935,lng:126.988884},
        {title: '종로돈까스', lat:37.570611,lng:126.990032},
        {title: '뚜레쥬르', lat:37.567057, lng:126.992175},
        {title: '고라니샌드위치', lat:37.566874,lng:126.992797},
        {title: '고라니카페', lat:37.567571,lng:126.992792},
        {title: '고라니도시락', lat:37.567405,lng:126.99336},
        {title: '고라니쥬스', lat:37.566890, lng:126.993377}
    ];

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
        console.log("가게 리스트: " + result);
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