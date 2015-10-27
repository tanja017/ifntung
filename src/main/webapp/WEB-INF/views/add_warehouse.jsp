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
        <h4 class="modal-title" id="myModalLabel">Add warehouse</h4>
    </div>
	<form method="post" action="save_warehouse" >
      <div class="modal-body" >
	    <input type="hidden" name="ID" value="">
		<div class="form-group">
	        <label class="col-sm-4 control-label">Name:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="name" value="">
	        </div>
	    </div>
	    <div class="form-group">
	        <label class="col-sm-4 control-label">Address:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="address" value="">
	        </div>
	    </div>
	    <!-- div class="form-group">
		<label class="col-sm-4 control-label"> Warehouse:</label>
			        <div class="col-sm-8">
							        	<select class="form-control" name="warehouse" id="search_list_wh">
							        		<option value="0">Warehouses</option>
							        		<c:forEach var="result" items="${ResultMap_WH}" varStatus="statusresult">
							        		<option value="${result.value.id}">${result.value.name}</option>
							        		</c:forEach>
							        	</select>
							        </div>
							    </div>
	    <div class="form-group">
	        <label class="col-sm-4 control-label">Number:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="number" value="">
	        </div>
	   	</div-->
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Add</button>
      </div>
   </form>