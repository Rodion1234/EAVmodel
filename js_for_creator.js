$('#sendId').on('click', function(){
    // var data = [], id;
    var data = [];
    // for( id in basket) {
    //     data.push({
    //       id: id,
    //       qty: basket[id]
    //     });
    // }
    $.ajax({
        url: '/order/add', // url куда отправлять
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response){
            alert("Hello");
        },
        processData: false
    });
});