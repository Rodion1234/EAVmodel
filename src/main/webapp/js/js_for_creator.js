
function getData() {


    var parentEl = document.getElementById("inp_id");
    var tableName = document.getElementById("tableName");



    var data = {
        'tableName': tableName.value,
        'columns': []
    };

    for (var i = 1; i <= parentEl.children.length - 1; i++) {
        var childrenEl = parentEl.children[i];
        var column = childrenEl.children[0];
        var valuecl = childrenEl.children[1];

        data.columns.push({'column': column.value, 'value': valuecl.value,'num': i});
    }

    return data;
}


function funk() {

    var data = getData();

    $.ajax({
        url: 'CreateOneTable', // url куда отправлять
        method: 'POST',
        contentType: 'application/json',
        dataType:'json',
        data: 'data=' + JSON.stringify(data),
        success: function (response) {
            alert(response.toString());
        },
        processData: false
    });


}