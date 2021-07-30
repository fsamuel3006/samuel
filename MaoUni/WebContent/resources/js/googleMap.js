
        let pos;
//        let data = [
//            { address: "220新北市板橋區懷德街139巷20弄19號旁停車場", apmId: "田單停車場", url: 'https://tw.portal-pokemon.com/play/resources/pokedex/img/pm/a42f1e83fdb6809384f2461670a1d81e227df05c.png' },
//            { address: "220新北市板橋區文化路二段255號", apmId: "楊家紅豆餅", url: 'https://tw.portal-pokemon.com/play/resources/pokedex/img/pm/a42f1e83fdb6809384f2461670a1d81e227df05c.png' },
//            { address: "220新北市板橋區大同街文聖街路口", apmId: "高手庭園保齡球館", url: 'https://tw.portal-pokemon.com/play/resources/pokedex/img/pm/a42f1e83fdb6809384f2461670a1d81e227df05c.png' },
//            { address: "220新北市板橋區永豐街121號", apmId: "樂翔港式飲茶", url: 'https://tw.portal-pokemon.com/play/resources/pokedex/img/pm/a42f1e83fdb6809384f2461670a1d81e227df05c.png' },
//            { address: "220新北市板橋區環河西路四段18號", apmId: "蝴蝶公園地景花海", url: 'https://tw.portal-pokemon.com/play/resources/pokedex/img/pm/a42f1e83fdb6809384f2461670a1d81e227df05c.png' }
//        ];
        
        let data = [];

        let svgMarker, image, shape;

        function init() {

            // 藍色勾勾
            svgMarker = {
                path: "M10.453 14.016l6.563-6.609-1.406-1.406-5.156 5.203-2.063-2.109-1.406 1.406zM12 2.016q2.906 0 4.945 2.039t2.039 4.945q0 1.453-0.727 3.328t-1.758 3.516-2.039 3.070-1.711 2.273l-0.75 0.797q-0.281-0.328-0.75-0.867t-1.688-2.156-2.133-3.141-1.664-3.445-0.75-3.375q0-2.906 2.039-4.945t4.945-2.039z",
                fillColor: "blue",
                fillOpacity: 0.6,
                strokeWeight: 0,
                rotation: 0,
                scale: 2,
                anchor: new google.maps.Point(15, 30),
            };

            // 旗標
            image = {
                url: "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png",
                // This marker is 20 pixels wide by 32 pixels high.
                size: new google.maps.Size(20, 32),
                // The origin for this image is (0, 0).
                origin: new google.maps.Point(0, 0),
                // The anchor for this image is the base of the flagpole at (0, 32).
                anchor: new google.maps.Point(0, 32),
            };

            sshape = {
                coords: [1, 1, 1, 20, 18, 20, 18, 1],
                type: "poly",
            };
            // 呼叫JS的Geolocation API取得經緯度(前提是使用者允許被存取位置)
            // 回傳一個 Geolocation 物件，透過這個物件可以存取Device的位置資訊

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    let marker = new google.maps.Marker({
                        position: pos,
                        icon: svgMarker,
                        map: map
                    });

                    map.setZoom(13);
                    map.setCenter(pos);

                    let cityCircle = new google.maps.Circle({
                        strokeColor: '#f1c40f', // 線條顏色
                        strokeOpacity: 1, // 線條透明度
                        strokeWeight: 1, // 線條粗度
                        fillColor: '#f1c40f', // 圓形裡填滿的顏色
                        fillOpacity: 0.35, // 圓形裡，填滿顏色的透明度
                        map: map,
                        center: pos, // 中心點
                        radius: 3000 // 半徑
                    });
                })
            } else {
                alert("請先允許存取位置！");
            }


            let geocoder = new google.maps.Geocoder();
            // let i = "新北市板橋區三民路一段一巷33號"; 
            for (let i = 0; i < data.length; i++) {
                getGeocode(data[i].address, data[i].apmId);
            }

            function getGeocode(address, ampId) {
                geocoder.geocode({ "address": address }, function (results, status) {
                    if (status == "OK") {
                        generateMarkers(results[0].geometry.location, ampId);
                    }
                })
            };



        }


        function generateMarkers(datum, apmId) {
            console.log(datum.lat());
            console.log(datum.lng());
            // 產生 marker 物件
            let marker = new google.maps.Marker({
                position: { lat: datum.lat(), lng: datum.lng() },
                icon: image,
                shape: shape,
                map: map,
                animation: google.maps.Animation.DROP, // DROP掉下來、BOUNCE一直彈跳
                draggable: false // true、false可否拖拉
            });

            // 產生 infowindow 物件
            let infowindow = new google.maps.InfoWindow({
                content: "<div class='info_title'>訂單編號： " + apmId + "</div>"
            });
            // 預設打開 info window
            // infowindow.open(map, marker);
            // 當 marker 被點擊時觸發
            marker.addListener('click', function () {
                infowindow.open(map, marker);
            });
        }



        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: { lat: 24.978391, lng: 121.268641 },
                zoom: 15,
            });
        }

        window.onload = init;