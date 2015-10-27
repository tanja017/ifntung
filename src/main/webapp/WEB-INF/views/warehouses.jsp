<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  --> 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    	<div class="row">
			<div class="col-sm-12">
				<h2>Warehouses list <span></span></h2>
					<div class="row plans">
    					<div class="box shadow">
        					<div class="box-body">
            					<div class="row">
            					<span class="pull-right modal-edit"  style="font-size: 20px; line-height: 40px;"  data-toggle="modal" data-item="0"><button type="button" data-target="#mainModal" style="border-radius: 5px!important;" class="b1 add-human"  id="add_warehouse" >&nbsp;Add</button></span>
								    <div class="col-md-12 ">
            							<table class="table table-hover">
								        	<thead>
										        <tr style="background: #ebebeb;">
										        	<th class="col-md-1">ID</th>
										        	<th class="col-md-4">Name</th>
										        	<th class="col-md-2">Action</th>
										        </tr>	
										    </thead>
										    <tbody id="warehouses-list">
												<c:forEach var="result" items="${ResultMap}" varStatus="statusresult">
									                <tr>
									             	<td>${result.value.id}</td>
									              	<td>${result.value.name}</td> 
									              	<td  class="list-actions1">
									              		<button type="button" class="b3 edit"  data-target="#mainModal" value="${result.value.id}" style="border-radius: 5px!important;">Edit</button>
														<a type="button" class="b2 delete" href="delete_warehouse?id=${result.value.id}" data-target="#mainModal" style="border-radius: 5px!important;">Delete</a>
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
    				</div>
    			</div>
    		</div>
    		<script>
	    		$(document).ready(function(){
	    		    
	    		    
	    		});
    		</script>
    </tiles:putAttribute>
</tiles:insertDefinition>