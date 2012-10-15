/**
 * This plug-in use for compare two div's elements text and color.
 * this plug-in depend on css class  red_span green_span default_span for select tag.
 * 
 * 
 * @author lixingya
 * @since 2011.6.10
 * 
 */
;(function ($){
	$.fn.comparetext = function(options){
		
		var settings = {
			'divId'    : '',
			'prefix'   : '',
			'clean'	   : false,
			'callback' : function(){return false;}
		};
		var methods = {
				checkText : function(){
					
					var sels = $('#'+ settings['divId']+' select option:selected');
					
					var array_sel = new Array();
					
					$(sels).each(function (j,sel){
						array_sel[j] = $(sel).parent().attr('id');
						$(sel).parent().addClass('colors');
						if($(sel).text() === $('#'+ settings['prefix'] + $(sel).parent().attr('id')).text()){
							$(sel).parent().parent().removeClass('red_span');
							$(sel).parent().parent().addClass('green_span');
							
							$("#"+settings['prefix'] + $(sel).parent().attr('id')).css('border','1px solid green');
						}else{
							$(sel).parent().parent().removeClass('green_span');
							$(sel).parent().parent().addClass('red_span');
							
							$("#"+settings['prefix'] + $(sel).parent().attr('id')).css('border','1px solid red');
						}
					});
					
					var eles = $('#' + settings['divId'] + ' :input');
					$(eles).each(function (i,ele){
						var srcId = $(ele).attr('id');
						
						var have_select = false;
						
						for(sel in array_sel){
							if(array_sel[sel] === srcId){
								have_select = true;
							}
						}
						if(!have_select){
							var descId = "#"+settings['prefix'] + srcId;
							if(($(descId).val()) != $(ele).val()){
								$(ele).css('border-color','red');
								$(descId).css('border-color','red');
							}else{
								$(ele).css('border-color','green');
								$(descId).css('border-color','green');
							}
							$(ele).blur(function(){
								if(($(descId).val()) != $(ele).val()){
									$(ele).css('border-color','red');
									$(descId).css('border-color','red');
								}else{
									$(ele).css('border-color','green');
									$(descId).css('border-color','green');
								}
							});
						}
					});
					array_sel = null;
				},
				cleanStyle : function(){
					var sels = $('#'+ settings['divId']+' select option:selected');
					
					$(sels).each(function (j,sel){
						$(sel).parent().parent().removeClass('red_span');
						$(sel).parent().parent().removeClass('green_span');
						$(sel).parent().parent().addClass('default_span');
					});
					
					var eles = $('#' + settings['divId'] + ' :input');
					$(eles).each(function (i,ele){
						$(ele).css('border-color','');
					});
				}
		}
		return this.each(function() {
			// If options exist, lets merge them
			// with our default settings
			if (options) {
				$.extend(settings, options);
			}
			
			$this = $(this);
			if(settings['clean']){
				methods.cleanStyle.apply(this);
			}else{
				methods.checkText.apply(this);
			}
			settings['callback'].apply(this);
		});
	}
})(jQuery);