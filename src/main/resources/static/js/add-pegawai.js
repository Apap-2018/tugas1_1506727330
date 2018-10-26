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
                    html += '<option value="' + data[i].id + '">'
                        + data[i].nama + '</option>';
                }
                html += '</option>';
                $('#namaInstansi').html(html);
            });
        });
    var idJabatan = 1;
    $('#tambahJabatanButton').click(function () {
        $('#jabatan0').clone().attr("id","jabatan"+idJabatan).attr("name","jabatanPegawaiModelList["+idJabatan+"].jabatan").appendTo('.jabatans');
        idJabatan+=1;
    });
});