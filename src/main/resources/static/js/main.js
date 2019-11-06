function projects_ajax_list(userId) {

    $("#listProjectsBtn").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/listProjects",
        data: {
            "userId" : userId
        },
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Projects</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#projects').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}
