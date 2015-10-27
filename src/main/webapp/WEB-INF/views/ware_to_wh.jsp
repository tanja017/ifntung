<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    	<div class="row">
				<div class="row form-horizontal">
		            <div class="col-md-9">
		                <div class="box shadow guide" data-bootstro-title="Step 7" data-bootstro-content="Here you will find all the necessary tools to implement your projects. Based on the 7-key model, you can create all the necessary analyzes and the business plan. Say goodbye to Word and Excel, from now you are able to work from anywhere on your idea!" data-bootstro-width="100%" data-bootstro-placement="bottom" data-bootstro-step="6">
		                    <div class="box-header">
		                        <h4>Add ware to "${WH_Res.name}"</h4>
		                    </div>
		                    <form action="save_add_ware" method="post" id="search-form">
							<input type="hidden" name="WH_id" value="<c:out value="${WH_Res.id}"/>">                	
		                	<div class="box-body tasks">
			                	<div class="form-group">
								<div class="box-body tasks">
							    <div class="form-group">
							        <label class="col-sm-4 control-label"> ID of ware:</label>
							       	<div class="col-sm-8">
	    								<input class="form-control" type="text" name="id_ware_add" value="<c:out value=""/>">
							        </div>		   	
							   	</div>
							   	<hr>
							    <div class="form-group">
							    	<label class="col-sm-4 control-label"> Number:</label>
	    							<div class="col-sm-8">
	    							<input class="form-control" type="text" name="wnumber_ware_add" value="<c:out value=""/>">
	    							</div>
							    </div>
				</div>
				</div>
		                	</div>
		                   	<div class="box-footer text-right">
		                   	<div class="modal-footer">
		                   		<button type="submit" class="btn btn-primary">Add</button>
		                   		<button type="button" class="btn btn-default" data-dismiss="modal">Cencel</button>
     						 </div>
		                   	</div>
		                   	</form>
		              	</div>
		            </div>
		      		
		      	</div>
    	</div>