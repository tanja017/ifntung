<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    	<div class="row">
				<div class="row form-horizontal">
		            <div class="col-md-9">
		                <div class="box shadow guide" data-bootstro-title="Step 7" data-bootstro-content="Here you will find all the necessary tools to implement your projects. Based on the 7-key model, you can create all the necessary analyzes and the business plan. Say goodbye to Word and Excel, from now you are able to work from anywhere on your idea!" data-bootstro-width="100%" data-bootstro-placement="bottom" data-bootstro-step="6">
		                    <div class="box-header">
		                        <h4>Make an order</h4>
		                    </div>
		                    <form action="save_custom" method="post" id="search-form">
		                	<div class="box-body tasks">
			                	<div class="form-group">
								<div class="box-body tasks">
							    <div class="form-group">
							        <label class="col-sm-4 control-label"> ID of ware:</label>
							       	<div class="col-sm-8">
	    								<input class="form-control" type="text" name="id_ware_custom" value="<c:out value=""/>">
							        </div>		   	
							   	</div>
							   	<hr>
							   	<div class="form-group">
							        <label class="col-sm-4 control-label"> Warehouse:</label>
							        <div class="col-sm-8">
							        	<select class="form-control" name="warehouse" id="search_list_wh">
							        		<option value="0">Warehouse</option>
							        		<c:forEach var="result" items="${ResultMap_WH}" varStatus="statusresult">
							        		<option value="${result.value.id}">${result.value.name}</option>
							        		</c:forEach>
							        	</select>
							        </div>
							    </div>
							    <hr>
							    <div class="form-group">
							    	<label class="col-sm-4 control-label"> Number:</label>
	    							<div class="col-sm-8">
	    							<input class="form-control" type="text" name="wnumber" value="<c:out value=""/>">
	    							</div>
							    </div>
				</div>
				</div>
		                	</div>
		                   	<div class="box-footer text-right">
		                   	<div class="modal-footer">
		                   		<button type="submit" style="border-radius: 5px!important;" class="b3 custom">Custom</button>
     						 </div>
		                   	</div>
		                   	</form>
		              	</div>
		            </div>
		      		
		      	</div>
    	</div>
    </tiles:putAttribute>
</tiles:insertDefinition>