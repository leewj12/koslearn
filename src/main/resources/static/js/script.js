// script.js

// 페이지가 로드된 후 버튼에 이벤트 리스너 추가
window.onload = function() {
  var button = document.getElementById('myButton');

  // 버튼 클릭 시 알림을 띄우는 이벤트 처리기
  button.addEventListener('click', function() {
    alert('버튼이 클릭되었습니다!');
  });
};
