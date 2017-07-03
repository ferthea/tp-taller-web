$(document).ready(function(){
    var ingredientes = [];
    $(".menu").each(function(){
        var ingredientes_menu = $(this).data("ingredientes").split(",");

        ingredientes_menu.forEach(function(ingrediente){
            if(jQuery.inArray(ingrediente, ingredientes) == -1){
                ingredientes.push(ingrediente)
            }
        })
    })

    $.each(ingredientes, function (i, item) {
        $('#filtroDeIngredientes').append($('<option>', {
            value: item,
            text : item
        }));
    });
    $('#filtroDeIngredientes').material_select();

    $('#filtroDeIngredientes').on('change', function() {
        $("input.select-dropdown[type=text]").each(function(){
            var filtro = $(this).val();
            if(filtro != "Selecciona ingrediente"){
                applyFilter(filtro);
            }else{
                $(".menu").each(function() {
                    $(this).fadeIn(400);
                })
            }
        })
    })

    $("#btn_reservar").click(function(e){
        e.prevent_default;
        console.log("TOQUE!");
        $("#form_menu").submit();
    })
})

function applyFilter(filtro){
    var filtro = filtro.split(",");
    $(".menu").each(function(){
        var ingredientes_menu = $(this).data("ingredientes");

        if(contieneIngredientes(filtro, ingredientes_menu)){
            $(this).fadeIn(400);
        }else{
            $(this).fadeOut(400);
        }
    })
}

function contieneIngredientes(filtro, ingredientes_menu){
    for(var i = 0; i < filtro.length; i++){
        var ingrediente = filtro[i];
        if(ingrediente.charAt(0) == " "){
            ingrediente = ingrediente.substr(1);
        }
        if(ingredientes_menu.indexOf(ingrediente) == -1){
            return false;
        }
    }
    return true;
}