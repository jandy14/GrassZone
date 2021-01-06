// document.addEventListener("DOMContentLoaded", function (event) {
//     const signinBtn = document.querySelector("form button#signin");
//     const signupBtn = document.querySelector("form button#signup");
//
//     function SignIn(event){
//         const data = new URLSearchParams();
//         for (const pair of new FormData(document.querySelector("form"))) {
//             data.append(pair[0], pair[1]);
//         }
//         fetch("/signin", {
//             method: "POST",
//             body: data,
//         }).then(function (res) {
//             return res.text();
//         }).then(function (text){
//             if(text == "success")
//             {
//                 window.location.href = "/board";
//             }
//             else if(text == "fail")
//             {
//                 alert("뭔가 잘못 입력");
//             }
//             else
//             {
//                 alert("처리되지 않은 오류");
//             }
//         }).catch(function (error) {
//             alert("알 수 없는 요류!!!");
//             window.location.href = '/';
//         })
//         event.preventDefault();
//     }
//     function GoSignUpPage(event){
//         window.location.href="/signup";
//         event.preventDefault();
//     }
//
//     signinBtn.addEventListener("click", SignIn);
//     signupBtn.addEventListener("click", GoSignUpPage);
// });
$(document).ready( function () {
    $("form button#signin").on("click", function(event) {
        event.preventDefault();
        $.ajax({
            url: "/signin",
            type: "POST",
            data: $("form").serialize(),
            success: function(result){
                if(result == "success")
                {
                    window.location.href = "/board";
                }
                else if(result == "fail")
                {
                    alert("뭔가 잘못 입력");
                }
                else
                {
                    alert("처리되지 않은 오류");
                }
            },
            error: function(){
                alert("알 수 없는 요류!!!");
                window.location.href = '/';
            },
        });
    });
    $("form button#signup").on("click", function(event) {
        window.location.href="/signup";
        event.preventDefault();
    });
});