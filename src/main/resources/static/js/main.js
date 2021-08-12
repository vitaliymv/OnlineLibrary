const form = document.getElementById("form");
form.addEventListener("submit", async event => {
    event.preventDefault();
    const formData = new FormData(form)
    const response = await fetch(form.action, {
        method: 'POST',
        body: formData
    })
    if (response.ok) {
        location.replace('/')
    }
});

$("#btnSearch").click(function () {
    let search = $("#search").val();
    if (search === " ") {
        alert("Please fill this field");
    } else {
        let url = "http://localhost:8080/user/search?title=" + search;
        $.get(url, function (data, status) {
            console.log(status)
        })
    }
})

$(".btn-danger").click(function (event) {
    event.preventDefault();
    let id = $(this).attr("id");
    let url = "http://localhost:8080/admin/delete?id=" + id;
    $.get(url, function (data) {
        if (data === 'ACCEPTED') {
            location.replace("/")
        }
    })
})



