// ボタンを押した時の処理
document.getElementById("btn").onclick = function(){
    // 位置情報を取得する
    navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
};



// 取得に成功した場合の処理
function successCallback(position){
    // 緯度を取得し画面に表示
    var latitude = position.coords.latitude;
    document.getElementById("latitude").innerHTML = latitude;
    // 経度を取得し画面に表示
    var longitude = position.coords.longitude;
    document.getElementById("longitude").innerHTML = longitude;
    // 緯度・経度の精度を取得し画面に表示
     var accuracy = position.coords.accuracy;
    document.getElementById("accuracy").innerHTML = accuracy;
};

// 取得に失敗した場合の処理
function errorCallback(error){
    alert("位置情報が取得できませんでした");
};