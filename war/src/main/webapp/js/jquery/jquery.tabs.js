/**
 * Defined a jQuery plug-in for tabs.
 * 
 * @author Cream
 * @since 1.5.0 2011-03-16
 */
;(function($){
	$.fn.selectedItemId = function() {
		return this.find('ul:first li.link a').attr('href');
	}
	$.fn.tabs = function(operation){

		var setting = {
			'defaultItem' : '0',
			'selected' : function(selectedItemId) {return false;}
		}

		return this.each(function() {
			
			if (operation) {
				$.extend(setting, operation);
			}

			var $tabs = $(this).find('ul:first li a');

			$tabs.removeClass('selected');
			$tabs.each(function(idx, el) {
				// bind event
				var id = $(this).attr('href');
				
				$(id).bind('cr.selected', function(event, shouldCallback) {
					$(el).parents('li:first').addClass('link');
					$(this).show();
					if (typeof shouldCallback === 'undefined') {
						shouldCallback = true;
					} 
					if (shouldCallback && setting['selected']) {
						setting['selected'].call(this, id);
					}
				});
				$(id).bind('cr.unselected', function() {
					$(el).parents('li:first').removeClass('link');
					$(this).hide();
				});
				if (idx == setting['defaultItem']) {
					$(this).addClass('selected');
					$(id).trigger('cr.selected');
				} else {
					$(id).trigger('cr.unselected');
				}
			});

			// Class operation
			$tabs.click(function() {
				$tabs.removeClass('selected');
				$(this).addClass('selected');
			
				// Div operation
				$tabs.each(function() {
					var id = $(this).attr('href');
					if ($(this).is('.selected')) {
						$(id).trigger('cr.selected');
					} else {
						$(id).trigger('cr.unselected');
					}

				});
				return false;
			});

			$tabs.bind('select', function() {
				$tabs.removeClass('selected');
				$(this).addClass('selected');
			
				// Div operation
				$tabs.each(function() {
					var id = $(this).attr('href');
					if ($(this).is('.selected')) {
						// false for not to run callback.
						$(id).trigger('cr.selected', false);
					} else {
						$(id).trigger('cr.unselected');
					}

				});
				return false;
			});

		});
	};
})(jQuery);