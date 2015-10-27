<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <html>
<body>
	<h2>Spring MVC and List Example</h2>

	<c:if test="${not empty lists}">

		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>${listValue}</li>
			</c:forEach>
		</ul>

	</c:if>
</body>
</html> -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  --> 
    	<div class="box-header">
    		<span>
		    	Goods placed on a selected warehouse
		    	<input type="hidden" name="WH_id" value="<c:out value="${WH_Res}"/>">
		    	<span class="pull-right modal-edit" style="font-size: 20px; line-height: 40px;"  data-toggle="modal" data-item="0"><button id="add_ware_to_wh" type="button" data-target="#mainModal" style="border-radius: 5px!important;" name="WH_id"  class="btn btn-default" value="<c:out value="${WH_Res}"/>">&nbsp;Add ware to warehouse</button></span-->
		    	<!-- button type="button" class="btn btn-warning move" href="order" data-target="#mainModal" value="${result.value.id}" style="border-radius: 5px!important;">Order ware</button-->
			</span>		 
		 </div>
    					
    					<div class="box shadow">
        					<div class="box-body">
            					<div class="row">
								    <div class="col-md-12 ">
								       	<table class="table table-condensed">
								        	<thead>
										        <tr style="background: #ebebeb;">
										        	<th class="col-md-1">ID</th>
										        	<th class="col-md-2">Ware</th>
										        	<th class="col-md-2">Number</th>
										        	<th class="col-md-7">Actions</th>
										        </tr>	
										    </thead>
										    <tbody id="warehouses-list">
												<c:forEach var="result" items="${Result}" varStatus="statusresult">
									                <tr>
									             	    <td>${result.value.id}</td>
														<td>${result.value.ware}</td> 
														<td>${result.value.wnumber}</td> 
														<td class="list-actions-move">
														<button type="button" class="b3 move"  data-target="#mainModal" value="${result.value.id}" style="border-radius: 5px!important;">Move</button>
														<a type="button" class="b2 delete" href="delete_ware_from?id=${result.value.id}" data-target="#mainModal" style="border-radius: 5px!important;">Delete</a>
														</td>
													</tr> 
									            </c:forEach>
											</tbody>	        	
										</table>
										<div class="text-center">
											<ul class="pagination">
											<c:forEach begin="0" end="${pagination}" var="val">
												  <li><a href="javascript:;">${val}</a></li>
											</c:forEach>
											</ul>
										</div>
									</div>
								</div>				
							</div>
    					</div>
    				

<!-- div id="search_form">
<tbody id="warehouses-list">
	<c:forEach var="result" items="${Result}" varStatus="statusresult">
	   <tr>
	<td>${result.value.id}</td>
	<td>${result.value.ware}</td> 
	<td>${result.value.warehouse}</td>
	<td>${result.value.number}</td> 
	</tr>
	</c:forEach><tr>
</tbody>
</div-->