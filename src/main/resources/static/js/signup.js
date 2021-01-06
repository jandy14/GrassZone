// document.addEventListener("DOMContentLoaded", function (event) {
//     const submitBtn = document.querySelector("form button#submit");
//
//     function submit(event) {
//         const data = new URLSearchParams();
//         for (const pair of new FormData(document.getElementById("signup"))) {
//             data.append(pair[0], pair[1]);
//         }
//         if(data.get("password") == data.get("confirm"))
//         {
//             data.delete("confirm");
//             fetch("/createuser", {
//                 method: "POST",
//                 body: data,
//             }).then(function (res) {
//                 return res.text();
//             }).then(function (text){
//                 if(text == "success")
//                 {
//                     alert("계정 생성 성공!");
//                     window.location.href = "/";
//                 }
//                 else if(text == "fail id")
//                 {
//                     alert("중복된 아이디!");
//                 }
//                 else
//                 {
//                     alert("처리되지 않은 오류");
//                 }
//             }).catch(function (error) {
//                 alert("알 수 없는 요류!!!");
//                 window.location.href = '/';
//             });
//         }
//         else
//         {
//             alert("비밀번호가 일치하지 않습니다.");
//         }
//         event.preventDefault();
//     }
//     submitBtn.addEventListener("click", submit);
// });
function FormToJson(serializedFormData) {
    let serial = serializedFormData;
    let formdata = {};
    serial.split("&").forEach(function (input){
        let splitdata = input.split("=");
        let key = splitdata[0];
        let value = splitdata[1];
        formdata[key] = value;
    });
    return formdata;
}
function JsonToForm(jsonData) {
    let result = "";
    for(key in jsonData)
    {
        if(result != "") { result += "&" }
        result += key + "=" + jsonData[key];
    }
    return result;
}
$(document).ready(function () {
    $("form button#submit").on("click", function (event) {
        event.preventDefault();

        let formdata = FormToJson($("#signup").serialize());

        if(formdata["password"] == formdata["confirm"])
        {
            delete formdata["confirm"];
            $.ajax({
                url: "/createuser",
                type: "POST",
                data: JsonToForm(formdata),
                success: function (text){
                    if(text == "success")
                    {
                        alert("계정 생성 성공!");
                        window.location.href = "/";
                    }
                    else if(text == "fail id")
                    {
                        alert("중복된 아이디!");
                    }
                    else if(text == "fail empty")
                    {
                        alert("칸은 채워야지!");
                    }
                    else
                    {
                        alert("처리되지 않은 오류");
                    }
                },
                error:function (error) {
                    alert("알 수 없는 요류!!!");
                    window.location.href = '/';
                },
            });
        }
        else
        {
            alert("비밀번호가 일치하지 않습니다.");
        }
    });
});