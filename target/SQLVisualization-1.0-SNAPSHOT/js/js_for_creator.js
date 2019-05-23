function clear() {
  document.getElementsByName('myForm')[0].reset();
}


function funk() {

    var parentEl = document.getElementById("inp_id");
    var tableName = document.getElementById("tableName");



    var data = {
        'tableName': tableName.value,
        'columns': []
    };
    var flag = true;
    for (var i = 1; i <= parentEl.children.length - 1; i++) {
        var childrenEl = parentEl.children[i];
        var column = childrenEl.children[0];
        var valuecl = childrenEl.children[1];
        if (column.value == "" || valuecl.value == "" || tableName.value == "") {
            flag = false;
        }
        data.columns.push({'column': column.value, 'value': valuecl.value, 'num': i});
    }

    if (flag == true) {
        $.ajax({
            url: 'CreateOneTable', // url куда отправлять
            method: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: 'data=' + JSON.stringify(data),
            success: function (response) {
                alert(response.data);
                 clear() ;
            },
            processData: false
        });
    }else {
        alert("Please, insert data!");
    }



}