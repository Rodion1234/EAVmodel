
function getData() {


    var parentEl = document.getElementById("inp_id");
    var tableName = document.getElementById("tableName");



    var data = {
        'tableName': tableName.value,
        'columns': []
    };
  
    // data['tableName'] = tableName.value;
    console.log(parentEl);
    for (var i = 1; i <= parentEl.children.length-1; i++) {
       var childrenEl = parentEl.children[i];
       var column = childrenEl.children[0];
       var valuecl = childrenEl.children[1];
        console.log(column);
        data.columns.push({'column': column.value, 'value': valuecl.value});
    }

    localStorage.setItem('data', JSON.stringify(data));

// Пример того, как можно преобразовать строку, полученную с помощью метода
// JSON.stringify() и сохранённую в localStorage обратно в объект
    var restoredSession = JSON.parse(localStorage.getItem('data'));

// Переменная restoredSession содержит объект, который был сохранён
// в localStorage
    console.log(restoredSession);

}


function funk() {

    var data;

    getData();
    $.ajax({
        url: 'CreateOneTable', // url куда отправлять
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {

            alert(response.obj);
        },
        processData: false
    });


}