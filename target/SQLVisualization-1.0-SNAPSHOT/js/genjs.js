function generateTable(dat) {
    var parent1 = document.getElementById("parEl1");
    var parent2 = document.getElementById("parEl2");
    var parent3 = document.getElementById("parEl3");
    var parent4 = document.getElementById("parEl4");



    for (var i = 0; i < dat.data.length; i++) {
        var newEl1 = document.createElement('tr');
        var newEl2 = document.createElement('tr');

        var nameEntity = dat.data[i].attribute.entity.nameEntity;
        var nameAttr = dat.data[i].attribute.nameAttribute;
        var idAttr = dat.data[i].attribute.idAttribute;
        var nameValue = dat.data[i].value;
        var idValue = dat.data[i].idValue;

        newEl1.innerHTML = "<td>" + nameEntity + "</td><td>" + nameAttr + "</td><td>" + nameValue + "</td>";
        newEl2.innerHTML = "<td>" + idValue + "</td><td>" + nameValue + "</td><td>" + idAttr + "</td>";

        parent1.appendChild(newEl1);
        parent4.appendChild(newEl2);

    }
    for (var i = 0; i < dat.listEnt.length; i++) {
        var newEl1 = document.createElement('tr');
        var idEntity = dat.listEnt[i].idEntity;
        var nameEntity = dat.listEnt[i].nameEntity;
        newEl1.innerHTML = "<td>" + idEntity + "</td><td>" + nameEntity + "</td>";
        parent2.appendChild(newEl1);
    }
    for (var i = 0; i < dat.listAttr.length; i++) {
        var newEl1 = document.createElement('tr');
        var idAttribute = dat.listAttr[i].idAttribute;
        var nameAttribute = dat.listAttr[i].nameAttribute;
        var idEntity = dat.listAttr[i].entity.idEntity;
        newEl1.innerHTML = "<td>" + idAttribute + "</td><td>" + nameAttribute + "</td><td>" + idEntity + "</td>";
        parent3.appendChild(newEl1);
    }


}
function loadData() {
    $.ajax({
        url: 'GenOneTable',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            console.log(response.toString());
            var dat = JSON.parse(response.toString());
            generateTable(dat);
        },
        processData: false
    });
}

window.onload = function () {
    loadData();
};