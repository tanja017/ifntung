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
        <h4 class="modal-title" id="myModalLabel">Edit ware</h4>
    </div>
	<form method="post" action="save_edit_ware" >
      <div class="modal-body" >
	    <input type="hidden" name="ID" value="${userData['id']}">
		<div class="form-group">
	        <label class="col-sm-4 control-label">Name:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="name" value="${userData['name']}">
	        </div>
	    </div>
	    <!-- div class="form-group">
	        <label class="col-sm-4 control-label">Age:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="age" value="${userData['Age']}">
	        </div>
	   	</div-->
	  </div>
      <div class="modal-footer">
        
        <button type="submit" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
   </form>