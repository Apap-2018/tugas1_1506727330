$(document).ready(function() {
    $('#provinsi').change(
        function() {
            $.getJSON("http://localhost:8080/instansi", {
                namaProvinsi : $(this).val(),
                ajax : 'true'
            }, function(data) {
                var html = '';
                var len = data.length;
                for ( var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].nama + '">'
                        + data[i].nama + '</option>';
                }
                html += '</option>';
                $('#namaInstansi').html(html);
            });
        });

});