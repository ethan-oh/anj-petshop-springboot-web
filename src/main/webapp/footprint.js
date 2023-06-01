// 커서 발자국생성
var footprintImage = "${pageContext.request.contextPath}/anjshop/src/main/webapp/pngwing.com.png"; // 발자국 이미지 경로
var footprintWidth = 10; // 발자국 이미지의 너비
var footprintHeight = 0; // 발자국 이미지의 높이 (자동 조정)
var intervalInSeconds = 0.5; // 발자국 생성 간격 (초)

var footprints = []; // 발자국 요소를 저장할 배열
var lastTimestamp = 0; // 마지막 발자국 생성 시간

document.addEventListener('mousemove', function(e) {
    var currentTimestamp = new Date().getTime();
    if (currentTimestamp - lastTimestamp >= intervalInSeconds * 200) {
        lastTimestamp = currentTimestamp;

        var xPos = e.clientX;
        var yPos = e.clientY;

        var footprint = document.createElement('img');
        footprint.setAttribute('src', footprintImage);
        footprint.setAttribute('class', 'footprint');
        footprint.style.left = xPos - (footprintWidth / 2) + 'px';
        footprint.style.top = yPos - (footprintHeight / 2) + 'px';

        document.body.appendChild(footprint);
        footprints.push(footprint);

        if (footprints.length > 4) {
            var removedFootprint = footprints.shift();
            document.body.removeChild(removedFootprint);
        }
    }
});