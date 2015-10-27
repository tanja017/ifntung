<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!--<c:forEach items="${userData}" var="list">
		<div class="form-group">
          	<label class="col-sm-4 control-label">${list.key}:</label>
          	<div class="col-sm-8">
           		<input class="form-control" type="text" name="name" value="${list.value}">
            </div>
        </div>
	</c:forEach>-->
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Wove ware</h4>
    </div>
	<form method="post" action="save_move_ware" >
      <div class="modal-body" >
	    <input type="hidden" name="ID" value="<c:out value="${ResultMap['id']}"/>">
		<div class="row form-horizontal">
        <div class="box shadow guide" data-bootstro-title="Step 7" data-bootstro-content="Here you will find all the necessary tools to implement your projects. Based on the 7-key model, you can create all the necessary analyzes and the business plan. Say goodbye to Word and Excel, from now you are able to work from anywhere on your idea!" data-bootstro-width="100%" data-bootstro-placement="bottom" data-bootstro-step="6">
		<div class="form-group">
		<div class="box-body tasks">
							    <div class="form-group">
							        <label class="col-sm-4 control-label"> Name of ware:</label>
							        <div class="col-sm-8">
							        	<input type="hidden" name="id" value="<c:out value="${ResultMap['id']}"/>">
							        	<input type="hidden" name="ware_id" value="<c:out value="${ResultMap['ware']}"/>">
							        	<input type="hidden" name="warehouse_id" value="<c:out value="${ResultMap['warehouse']}"/>">
							        	<label class="col-sm-4 control-label" name="ware_id" id="<c:out value="${ResultMap['ware']}"/>">${ResultMap['name']}</label>
									</div>					   	
							   	</div>
							   	<hr>
							   	<div class="form-group">
							        <label class="col-sm-4 control-label"> Move on:</label>
							        <div class="col-sm-8">
							        	<select class="form-control" name="warehouse" id="search_list_wh">
							        		<option value="0">Warehouses</option>
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
	    							<input class="form-control" type="text" name="wnumber" value="<c:out value="${ResultMap['wnumber']}"/>">
	    							</div>
							    </div>
				</div>
				</div>
			</div>
	        <!-- div class="col-sm-8">
	        <label class="col-sm-4 control-label">Name:</label>
	        <div class="col-sm-4">
	            <label class="col-sm-4 control-label" id="<c:out value="${ResultMap['id']}"/>">${ResultMap['name']}</label>
	           <!-- input class="form-control" type="text" name="name" value="<c:out value="${ResultMap['name']}"/>">
	        </div>
	        </div>
	        <hr>
	       <div class="form-group">
							        <label class="col-sm-4 control-label">Move to:</label>
							        <div class="col-sm-8">
							        	<select class="form-control" name="warehouse" id="search_list_wh">
							        		<option value="0">Warehouses</option>
							        		<c:forEach var="result" items="${ResultMap_WH}" varStatus="statusresult">
							        		<option value="${result.value.id}">${result.value.name}</option>
							        		</c:forEach>
							        	</select>
							           	<!--  input class="form-control" type="text" name="name" value=""-->
							        </div>
							    </div-->	
	    </div>
	    <!-- div class="form-group">
	        <label class="col-sm-4 control-label">Age:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="age" value="${userData['Age']}">
	        </div>
	   	</div-->
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
   </form>