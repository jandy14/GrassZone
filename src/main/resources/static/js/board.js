function GetPageAmount(postAmount, postCountInPage){
    return parseInt((postAmount - 1) / postCountInPage) + 1;
}
function SetPosts(pageNum = 1, boardID = 0, postCountInPage = 5) {
    let sendData = "postCountInPage=" + postCountInPage;

    $.ajax({
        url: "/post/page/" + boardID + "/" + pageNum,
        type: "GET",
        data: sendData,
        success: function (result) {
            $("#posts tbody").append(result);
        },
        error: function (){
            throw new Error("게시글 불러오기 실패");
        }
    })
}
function ClearPosts() {
    $("#posts tbody")[0].innerHTML = "";
}
function ClearPageNavigation() {
    $("#pages thead tr")[0].innerHTML = "";
}
function SetPageNavigation(pageNum = 1, boardID = 0, postCountInPage = 5) {
    let sendData = "boardID=" + boardID;
    $.ajax({
        url: "/post/amount",
        type: "GET",
        data: sendData,
        success: function(result) {
            for(let i = 1; i <= GetPageAmount(parseInt(result),postCountInPage); ++i)
            {
                let td = "<td value='" + i + "'>" + i + "</td>"
                $("#pages thead tr").append(td);
            }
        },
        error: function(error) {
            throw new Error("게시글 갯수 불러오기 실패");
        }
    });
}
$(document).ready( function () {
    const url = new URL(window.location.href);
    let boardID = url.searchParams.get("boardID");
    let pageNum = url.searchParams.get("pageNum");
    let postCountInPage = 5;

    if (boardID == null) boardID = 0;
    if (pageNum == null) pageNum = 1;
    if (postCountInPage == null) postCountInPage = 5;

    $("#pages")[0].setAttribute("value", boardID);

    ClearPosts();
    SetPosts(pageNum, boardID, postCountInPage);
    SetPageNavigation(pageNum,boardID,postCountInPage);

    $("#pages thead tr").on("click", "td", function(event) {
        event.preventDefault();
        const boardID = event.target.closest("#pages").getAttribute("value");
        const pageNum = event.target.getAttribute("value");
        ClearPosts();
        SetPosts(pageNum, boardID, postCountInPage);
    });
    $("#boards thead tr").on("click", "td", function(event) {
        event.preventDefault();
        const boardID = event.target.getAttribute("value");
        const query = "?boardID=" + boardID + "&pageNum=" + 1;
        window.history.pushState(null,null, "/board" + query)
        ClearPosts();
        SetPosts(1, boardID, postCountInPage);
        ClearPageNavigation();
        SetPageNavigation(1, boardID, postCountInPage);
    });
    $(window).on("popstate", function(event) {
        event.preventDefault();
        const urlParams = new URLSearchParams(window.location.search);
        let boardID = urlParams.get("boardID");
        let pageNum = urlParams.get("pageNum");
        if (boardID == null) boardID = 0;
        if (pageNum == null) pageNum = 1;

        ClearPosts();
        SetPosts(pageNum, boardID, postCountInPage);
        ClearPageNavigation();
        SetPageNavigation(pageNum, boardID, postCountInPage);
    })
});
