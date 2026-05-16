// scripts.js

// 페이지가 로드될 때 실행되는 함수
document.addEventListener("DOMContentLoaded", function () {
    // 버튼 클릭 시 alert를 띄우는 기능
    const button = document.getElementById("myButton");
    button.addEventListener("click", function () {
        alert("버튼이 클릭되었습니다!");
    });
});