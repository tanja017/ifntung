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
        <h4 class="modal-title" id="myModalLabel">Edit warehouse</h4>
    </div>
	<form method="post" action="save_edit_warehouse" >
      <div class="modal-body" >
	    <input type="hidden" name="ID" value="<c:out value="${ResultMap['id']}"/>">
		<div class="form-group">
	        <label class="col-sm-4 control-label">Name:</label>
	        <div class="col-sm-8">
	           	<input class="form-control" type="text" name="name" value="<c:out value="${ResultMap['name']}"/>">
	        </div>
	    </div>
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
   </form>