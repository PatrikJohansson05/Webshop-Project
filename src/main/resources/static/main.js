
$('document').ready(function() {

    $('.table .btn-warning').on('click',function(event){

        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(product, status){
            $('#IdEdit').val(product.id);
            $('#nameEdit').val(product.name);
            $('#departmentEdit').val(product.price);
        });

        $('#editModal').modal();

    });

    $('.table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();

    });

});