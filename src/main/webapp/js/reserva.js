$(document).ready(function(){
    $("#btn_reservar").click(function(e){
        e.prevent_default;
        $("#form_menu").submit();
    })

    $(".agregar_menu").click(function(){
        let _self = $(this);
        let restaurant = $("#id_restaurant").val();
        let esta_activo = _self.data("activo");

        if(esta_activo === undefined || esta_activo == 0){
            marcarComoAgregado(_self, restaurant);
        }

        if(esta_activo == 1){
            marcarComoBorrado(_self, restaurant);
        }
        console.log($("#lista_menu").val());
    })
})

function marcarComoAgregado(element, restaurant){
    let menu = element.data("id_menu");

    element.removeClass("light-grey-text");
    element.addClass("light-green-text ligthen-2");
    agregarOQuitarMenu(restaurant, menu);
    element.data("activo", "1");
}

function marcarComoBorrado(element, restaurant){
    let menu = element.data("id_menu");

    element.removeClass("light-green-text ligthen-2");
    element.addClass("light-grey-text");
    agregarOQuitarMenu(restaurant, menu)
    element.data("activo", "0");
}

function agregarOQuitarMenu(restaurant, menu){
    let lista = $("#lista_menu");

    if(lista.val().indexOf(menu) > -1){
        lista.val(lista.val().replace(menu+",", ""));
    }else{
        lista.val(lista.val()+menu+",");
    }

    console.log("Lista: " + lista.val());

}