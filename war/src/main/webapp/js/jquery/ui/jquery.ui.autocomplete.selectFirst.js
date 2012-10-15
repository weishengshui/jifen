/**
 * From http://github.com/scottgonzalez/jquery-ui-extensions/blob/master/autocomplete/jquery.ui.autocomplete.selectFirst.js
 * <br/> Depends on jQuery.ui.autocomplete.js. It will add "selectFirst"
 * operation for autocomplete to select the first item.
 * 
 * @author cream
 * @since 1.0.0 2010-08-03
 */
(function( $ ) {

$( ".ui-autocomplete-input" ).live( "autocompleteopen", function() {
var autocomplete = $( this ).data( "autocomplete" ),
menu = autocomplete.menu;

if ( !autocomplete.options.selectFirst ) {
return;
}

menu.activate( $.Event({ type: "mouseenter" }), menu.element.children().first() );
});

}( jQuery ));