// document.addEventListener("DOMContentLoaded", function(){
//     document.getElementById("btn").addEventListener("click", function(event) {
//
//         const data = new URLSearchParams();
//         for (const pair of new FormData(document.getElementById("post"))) {
//             data.append(pair[0], pair[1]);
//         }
//
//         fetch("/post/submit", {
//             method: "POST",
//             body: data,
//         }).then(function (res) {
//             return res.text();
//         }).then(function (text){
//             if(text == "success") {
//                 alert("작성 완료!!");
//                 window.location.href = '/';
//             }
//             else if(text == "fail login") {
//                 window.location.href = '/';
//             }
//             else {
//                 alert("처리되지 않은 오류");
//             }
//         }).catch(function (error) {
//             alert("알 수 없는 오류");
//         });
//         event.preventDefault();
//     });
// });
$(document).ready( function () {
    $("#btn").on("click", function(event) {
       event.preventDefault();
        $.ajax({
            url: "/post/submit",
            type: "POST",
            data: $("#post").serialize(),
            success: function(result){
                if(result == "success") {
                    alert("작성 완료!!");
                    window.location.href = '/board';
                }
                else if(result == "fail login") {
                    window.location.href = '/';
                }
                else {
                    alert("처리되지 않은 오류");
                }
            },
            error: function(){
                alert("알 수 없는 오류");
            },
        });
    });
});