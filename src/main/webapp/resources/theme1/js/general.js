

function loadData(params, action, container, type_insert) 
{
	loadAsync(params, action, container, type_insert);
}


function loadAsync (params, action, container, type_insert)
	{
	
		$.ajax({
			  type: "GET",
			  url: "/springmvc/"+action,
			  data: params,
			  success: function(data){
				    $(container).html(data);
				  }
			});
	}


function loadAsyncPost (params, action, container, type_insert)
{

	$.ajax({
		  type: "POST",
		  url: "./"+action,
		  data: params,
		  mimeType:"text/html; charset=UTF-8",
		  success: function(data){
			  //alert('PDF created succesful');
			  }, error: function (xhr, ajaxOptions, thrownError) {
			        alert(xhr.status);
			        alert(ajaxOptions);
			      }
		});
}


function loadAsyncPDF (params, action, filename)
{

	$.ajax({
		  type: "GET",
		  url: "/springmvc/"+action,
		  success: function(data){
			  loadAsyncPost({pdf: data}, params);
		  }
		});
}


$(document).ready(function(){
	
	$('body').on('click', '#printWares', function(){

		loadAsyncPDF('printWaresList', 'getWares');
		
	});
	
	$('body').on('click', '#printExcel', function(){
		
		loadAsync('', 'printExcel');
		//alert('Excel file created succesfuly.');
		
	});
	
	$('body').on('click', '#printJasper', function(){
		
		loadAsync('', 'printJasper');
		
	})
	
	
	
	$('body').on('click','#getList', function()
	        {
		loadData('', 'list', '.body');
	        });
	
	$('body').on('click','.registration', function()
	    {
				$('.login-block').toggle();
				$('.reg-block').show();
	    });
	
	$('body').on('click','.pagination li a', function(){
		var page = $(this).html();
	    loadData({page: page}, 'list', '#customer-list');
	});
	
	/*$('body').on('click','.list-actions .edit', function(){
		var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");
		loadData({userID: elem}, 'edit_customer', '#main-modal-insert');
		$(modal).modal('show');
	});*/
	
	$('body').on('click','.list-actions .edit', function(){
		var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");
		loadData({wareID: elem}, 'edit_wares', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	$('body').on('click','.list-actions1 .edit', function(){
		var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");
		loadData({whouseid: elem}, 'edit_warehouse', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	/*$('body').on('click','.add-human', function(){
		var modal = $(this).attr("data-target");
		loadData('', 'add_human', '#main-modal-insert');
		$(modal).modal('show');
	});*/
	
		
	$('body').on('click','#search-list-w', function(){
		var post = $('#search-form').serialize();
		loadData(post, 'search_list_w', '#search_result');
	});
	
	$('body').on('change','#search_list_wh', function(){
		var elem = $(this).prop("value");
		loadData({warehouse: elem}, 'search_list_wh', '#search_result');
	});
	
	$('body').on('click','.list-actions-move .move', function(){
		var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");
		loadData({id: elem}, 'move_ware', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	$('body').on('click','#custom', function(){
		/*var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");*/
		loadData('', 'custom', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	$('body').on('click','.save_custom', function(){
		var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");
		loadData({id: elem}, 'save_custom', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	$('body').on('click','#add_ware', function(){
		var modal = $(this).attr("data-target");
		loadData('', 'add_ware', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	$('body').on('click','#add_warehouse', function(){
		var modal = $(this).attr("data-target");
		loadData('', 'add_warehouse', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	$('body').on('click','#add_ware_to_wh', function(){
		var elem = $(this).prop("value");
		var modal = $(this).attr("data-target");
		loadData({WH_id: elem},'add_ware_to_wh', '#main-modal-insert');
		$(modal).modal('show');
	});
	
	
});