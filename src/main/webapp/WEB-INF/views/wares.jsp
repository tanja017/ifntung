<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  --> 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
     <!-- style>
	   .b1 {
	    background: lightsteelblue; /* Синий цвет фона */ 
	    color: midnightblue; /* Белые буквы */ 
	    font-size: 9pt; /* Размер шрифта в пунктах */
	    
	   		}
	   	.b2 {
	    background: lightblue; /* Синий цвет фона */ 
	    color: midnightblue; /* Белые буквы */ 
	    font-size: 9pt; /* Размер шрифта в пунктах */
	    font-size: 13px;
    	padding: 5px 12px;
   		height: 30px;
	    border-radius: 0!important;
	    border: none;
	    border-bottom: 1px dashed #999;
	    font-family: inherit;
   		font-size: inherit;
    	line-height: inherit;
    	    text-transform: none;
    	        -webkit-appearance: button;
    	cursor: pointer;
	   		}
	   	.b3 {
	    background: lightsteelblue; /* Синий цвет фона */ 
	    color: midnightblue; /* Белые буквы */ 
	    font-size: 9pt; /* Размер шрифта в пунктах */
	    font-size: 13px;
    	padding: 5px 12px;
   		height: 30px;
	    border-radius: 0!important;
	    border: none;
	    
	   		}	
  	</style-->
    	<div class="row">
			<div class="col-sm-12">
				<h2>Wares list <span></span></h2>
					<div class="row plans">
    					<div class="box shadow">
        					<div class="box-body">
            					<div class="row">
            					<!-- div class="add_action"-->
            					<span class="pull-right" style="font-size: 20px; line-height: 40px;"  data-toggle="modal" data-item="0">
            					<button id="printWares" background="grey" type="button" data-target="#mainModal" style="border-radius: 5px!important;" class=" b1">&nbsp;PDF</button>
            					<button id="printExcel" background="grey" type="button" data-target="#mainModal" style="border-radius: 5px!important;" class=" b1">&nbsp;Excel</button>
            					<button id="printJasper" background="grey" type="button" data-target="#mainModal" style="border-radius: 5px!important;" class=" b1">&nbsp;JasrperReports</button>
            					<button id="add_ware" background="grey" type="button" data-target="#mainModal" style="border-radius: 5px!important;" class=" b1">&nbsp;Add ware</button></span>
								<!-- /div-->
								    <div class="col-md-12 ">
            							<table class="table table-hover">
								        	<thead>
										        <tr style="background: #ebebeb;">
										        	<th class="col-md-1">ID</th>
										        	<th class="col-md-4">Name</th>
										        	<th class="col-md-2">Action</th>
										        </tr>	
										    </thead>
										    <tbody id="wares-list">
												<c:forEach var="contact" items="${wareslist}" varStatus="status">
									        	<tr>
													<td>${contact.wareId}</td>
													<td>${contact.name}</td>
													<td class="list-actions">
														<button type="button" class="b3 edit"  data-target="#mainModal" value="${contact.wareId}" style="border-radius: 5px!important;">Edit</button>
														<a type="button" class="b2 delete" href="delete_ware?id=${contact.wareId}" data-target="#mainModal" style="border-radius: 5px!important;">Delete</a>
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