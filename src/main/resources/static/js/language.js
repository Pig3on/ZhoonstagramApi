$(function() {

    $( "#language-change" ).click(function() {
        const value = $("#language-select option:selected").val();
        const oldUrl = location.protocol + '//' + location.host + location.pathname;
        window.location.replace(oldUrl + "?lang=" + value)
    });
});