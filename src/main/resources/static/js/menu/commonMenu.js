$(document).ready(function() {
    debugger;
    $.ajax({
        type: 'GET',
        url: '/menu/',
        dataType: "text"
    })
    .done(function(data) { // 서버요청이 성공시의 콜백함수
        $('#menu-navbar')[0].innerHTML = data;
    })
    .fail(function(request, status, error) { // 서버요청이 에러시의 콜백함수
        alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
    })
    .always(function() { // 항상 실행 (finally 같은느낌)
    });
});