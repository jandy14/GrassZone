function SetComments(postNumber) {
    $.ajax({
        url: "/comment/" + postNumber,
        type: "GET",
        success: function(result){
            // const trStart = "<tr>";
            // const trEnd = "</tr>";
            // const tdStart = "<td>";
            // const tdEnd = "</td>";
            //
            // for(key in result) {
            //     let comment = "";
            //     comment += trStart;
            //     comment += tdStart + result[key]["authorName"] + tdEnd;
            //     comment += tdStart + result[key]["contents"] + tdEnd;
            //     comment += tdStart + result[key]["creationTime"] + tdEnd;
            //     comment += trEnd;
            //     $("#comments tbody").append(comment);
            // }
            $("#comments tbody").append(result);
        },
        error: function (){
            throw new Error("댓글 불러오기 실패");
        }
    });
}

$(document).ready( function () {
    const pathArray = window.location.pathname.split('/');
    const postNumber = pathArray[pathArray.length -1];

    $('form#comment button#btn').on('click', function(e){
        e.preventDefault();

        $.ajax({
            url: "/comment/submit",
            type: "POST",
            data: $("form#comment").serialize(),
            success: function(result){
                alert("댓글 달기 성공");
                $("form#comment #contents")[0].value = "";
                $("#comments tbody")[0].innerHTML = "";
                SetComments(postNumber);
            },
            error: function(){
                alert("댓글 달기 실패");
            },
        });
    });

    SetComments(postNumber);
});