/**
 * Defined a jQuery plug-in for file upload.
 * 
 * Depends:
 *   jquery.form.js
 *   showDialog.js
 * 
 * TODO should rewrite showDialog in this jQuery plug-in
 * 
 * @author Cream
 * @since 1.5.0 2011-03-16
 */
;(function($) {
	
	$.fn.fileupload = function(options) {
		
		// Defined return value to save the result.
		var returnValue;
		
		var settings = {
				'title' : '文件',
				'tips' : '文件上传将没有预览',
				'type' : 'file', // 'file', 'image'
				/*
				 * Split with ','. Such as : '.jpg, .png, .gif'. 
				 * Will not check the upload file type when this attribute set nothing.
				 */
				'allowType' : '',
				
				// dialog setting
				'dialogTitle' : '文件',
				'dialogTips' : '文件上传将没有预览',
				'action' : '',
				'showImgURL' : '',
				'defaultImgURL' : '',
				'loadingImgURL' : '',
				'docSuccessTipImgURL' : '',
				'listTableId' : '', // without '#'
				'callback' : function() {return false;}
		};
		
		// tools.
		var buildId = function(baseId) {
			// Create new ID with type. Split with '_'.
			return [baseId, settings['type']].join('_');
		}

		/**
		 * Always return <code>true</code> if user not set 'allowType'. Return
		 * <code>false</code> when input file type not in the list of
		 * 'allowType', other ways return <code>true</code>.
		 */
		var checkAllowType = function(fileType) {
			// Check whether the attribute 'allowType' had been set.
			if (!settings['allowType']) {
				return true;
			}
			// 'allowType' will be split with ','.
			var types = settings['allowType'].split(',');
			for (var t =0;t<types.length;t++) {
				if (fileType === jQuery.trim(types[t])) {
					return true;
				}
			}
			return false;
		}
		
		var methods = {
				showDialog : function() {
					// empty return value.
					returnValue = {};
					// reset preview image
					$('.image_show_img').attr('src', settings['defaultImgURL']);
					
					showDialog.show(buildId('fileupload'));
					$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].submit_btn'].join(' '))
						.css('color','gray');
					$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].continue_btn'].join(' '))
						.css('color','gray');
				},
				hideDialog : function(onclosing) {
					if (typeof onclosing === "function") {
						onclosing.call(this, returnValue);
					}
					$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].submit_btn'].join(' ')).css('cursor', 'not-allowed').unbind();
					$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].continue_btn'].join(' ')).css('cursor', 'not-allowed').unbind();
					$([buildId('#fileupload'), '.foruploadpath'].join(' ')).val('');
					$([buildId('#fileupload'), '.upload'].join(' ')).val('');
					showDialog.hide(buildId('fileupload'));		
					
					$([buildId('#fileupload'), '.success_tip'].join(' ')).html('');
					
				},
				drawDialog : function() {
					// build HTML to the end of body

					// build table
					$table = $('<table width="100%" height="200" border="0" cellpadding="0" cellspacing="0" class="uppictable"></table>').append($(''));
					$('<tr></tr>')
						.append($(['<td height="37" colspan="3">', settings['dialogTips'], '</td>'].join('')))
						.appendTo($table);
					$('<tr></tr>')
						.append($('<td colspan="2" align="left"><input type="text" class="foruploadpath bigtext" readonly="readonly" value=""/></td>'))
						.append($(['<td width="460" align="center">',
								  '<div class="floatfile">',
						            '<div class="excelfile" >',
						              '<input name="upload"  type="file" size="0" class="upload" />',
						            '</div>',
							        '<input type="button" class="big_button" value="浏 览" />',
								  '</div>',
								'</td>'].join('')))
						.appendTo($table);
					
					if (settings['type'] === 'image') {
						$('<tr></tr>')
							.append('<td width="13%" align="right" valign="top">图片预览：</td>')
							.append(['<td width="59%" valign="top">',
									  '<input type="hidden" name="fileNameId" class="fileNameId"/>',
							          '<div class="showpic image_show_div">',
							          '<img class="image_show_img" src="', settings['defaultImgURL'], '" width="118" height="78" />',
							          '</div>',
									'</td>'].join(''))
							.append('<td>&nbsp;</td>')
							.appendTo($table);
					}else{
						$('<tr></tr>')
						.append('<td width="13%" align="right" valign="top" class="success_tip"></td>')
						.append(['<td width="59%" valign="top">',
								  '<input type="hidden" name="fileNameId" class="fileNameId"/>',
						          '<div class="showpic image_show_div">',
						          '<img class="image_show_img" src="#" width="118" height="78" style="display:none"/>',
						          '</div>',
								'</td>'].join(''))
						.append('<td></td>')
						.appendTo($table);
					}
					// build form
					var $form = $('<form></form>').attr({
							action : settings['action'],
							method : 'POST'
						}).addClass('up_file_form');
					$form.append([
					              '<div class="shadow_titlte"><a href="javascript:void(0);" title="关闭" class="close_dialog">关闭</a>',
					              settings['dialogTitle'],
					              '</div>'
					              ].join(''));
					$form.append($('<div class="pic_content"></div>').append($table));
					
					// Save button
					var $enterBtn = $('<input type="button" />')
						.css('cursor', 'not-allowed')
						.addClass('big_button')
						.addClass('submit_btn')
						.val('确定');
					var $continueBtn = $('<input type="button" />')
						.css('cursor', 'not-allowed')
						.addClass('big_button')
						.addClass('continue_btn')
						.css('width', '80px')
						.val('继续上传');
					var $saveBtn = $('<div></div>')
						.addClass('shadow_post')
						.append($enterBtn)
						.append($continueBtn)
						.append('<input type="button" class="big_button cancel_btn" value="取消" />');
					// Append dialog form to the bottom of body.
					$('<div></div>')
						.attr('id', buildId('fileupload'))
						.css('display', 'none')
						.addClass('up_pic')
						.append($form)
						.append($saveBtn)
						.appendTo($('body'));
				},
				bindEvent : function() {
					// add event handler
					$([buildId('#fileupload'), '.close_dialog'].join(' ')).click(function() {
						methods.hideDialog.apply(this);
					});
					
					// add event for input:upload
					$([buildId('#fileupload'), '.upload'].join(' ')).change(function() {
						$([buildId('#fileupload'), '.foruploadpath'].join(' '))
							.val($([buildId('#fileupload'), '.upload'].join(' ')).val());

						$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].submit_btn'].join(' '))
							.css('cursor', 'not-allowed')
							.unbind();
						
						$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].continue_btn'].join(' '))
							.css('cursor', 'not-allowed')
							.unbind();

						var fileObj = $([buildId('#fileupload'), '.upload'].join(' '))[0];
						
						// check file type
						var fileext=fileObj.value.substring(fileObj.value.lastIndexOf("."),fileObj.value.length);
						fileext=fileext.toLowerCase();
						
						if (!checkAllowType(fileext)){
							alert("对不起，系统不支持您所上传的文件格式。\n你上传的文件格式为：" + fileext);
							fileObj.value="";
							fileObj.focus();
							$([buildId('#fileupload'), '.foruploadpath'].join(' ')).val('');
							$([buildId('#fileupload'), '.image_show_img'].join(' ')).hide();
						}else{
							//上传处理
							var options = {
								dataType : "json",
							    url : settings['action'],
							    beforeSubmit : function(){
							    		$([buildId('#fileupload'), '.image_show_img'].join(' ')).show();
							    		$([buildId('#fileupload'), '.image_show_img'].join(' ')).attr('src',settings['loadingImgURL']);
							    		$([buildId('#fileupload'), '.image_show_img'].join(' ')).css('border',0);
							    		$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].submit_btn'].join(' '))
											.css('color','gray');
							    		$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].continue_btn'].join(' '))
											.css('color','gray');
							    	},
							    success : function(data) {
							    	
							    	$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].submit_btn'].join(' ')).css('color','#444444');
							    	$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].continue_btn'].join(' ')).css('color','#444444');
							    	
							    	returnValue = data;
							    	// Had error from action.
							    	if (data.errorMsg) {
							    		alert(data.errorMsg);
							    	} else {
							    		if (settings['type'] === 'image') {
									    	if (data.fileId) {
									    		imgUrl = settings['showImgURL'] + '?fileId=' + data.fileId;
									    		$([buildId('#fileupload'), '.image_show_img'].join(' ')).attr('src', imgUrl);
									    	} else {
									    		$([buildId('#fileupload'), '.image_show_img'].join(' ')).attr('src', settings['defaultImgURL']);
									    	}
								    	}else{
								    		$([buildId('#fileupload'), '.image_show_img'].join(' ')).hide();
								    		$([buildId('#fileupload'), '.success_tip'].join(' ')).html(['<img src="',settings['docSuccessTipImgURL'],'" />'].join(' '));
								    		$([buildId('#fileupload'), '.success_tip','img'].join(' ')).css('border',0);
								    	}

										// Event for uploadfile input click.
							    		$([buildId('#fileupload'), '.upload'].join(' ')).click(function(){
							    			$([buildId('#fileupload'), '.success_tip'].join(' ')).html('');
							    		});
							    		
							    		// Event for enter button.
										$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].submit_btn'].join(' '))
											.css('cursor', 'pointer')
											.click(function(){
												methods.hideDialog.call(this, settings['callback']);
												$([buildId('#fileupload'), '.success_tip'].join(' ')).html('');
										});
										
										// Event for continue button.
										$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].continue_btn'].join(' '))
											.css('cursor', 'pointer')
											.click(function(){
												methods.hideDialog.call(this, settings['callback']);
												methods.showDialog();
												$([buildId('#fileupload'), '.success_tip'].join(' ')).html('');
										});
							    	}
								}
							};
							
							$([buildId('#fileupload'), '.up_file_form'].join(' ')).unbind();
							$([buildId('#fileupload'), '.up_file_form'].join(' ')).ajaxForm(options);
							$([buildId('#fileupload'), '.up_file_form'].join(' ')).submit();
						}
					});
					
					// Event for cancel button.
					$([buildId('#fileupload'), '.shadow_post', 'input[type="button"].cancel_btn'].join(' ')).click(function(){
						methods.hideDialog.apply(this);
					});
				},
				init : function() {
					// Check the type to create 'up_file' or 'up_pic' if the div not existed.
					if (!$(buildId('#fileupload')).length) {
						methods.drawDialog.apply(this);
					}
					
					// Bind click event for all 'del_upload_file_btn' class.
					$('.del_upload_file_btn').unbind();
					$('.del_upload_file_btn').live('click', function() {
						$(this).parents('tr:first').remove();
					});
					
					// Bind event handler for dialog.
					methods.bindEvent.apply(this);
				}
		}
		
		return this.each(function() {
			// If options exist, lets merge them
			// with our default settings
			if (options) {
				$.extend(settings, options);
			}
			
			$this = $(this);
			
			// plug-in code here
			methods.init.apply(this);
			
			// Handler click event
			$(this).click(function(){
				methods.showDialog.apply(this);
			});
		});
	}
})(jQuery);