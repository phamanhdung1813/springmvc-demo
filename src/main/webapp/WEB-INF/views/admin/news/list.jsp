<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<c:url var="adminHome" value="/admin/home"/>
<c:url var="newAPI" value="/api/new"/>
<c:url var="newURL" value="/admin/news/list"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	       
    <div class="main-content">
    <form action="<c:url value='/admin/news/list'/>" id="formSubmit" method="get">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${adminHome}">Admin Page</a>
							</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty message}">
									<div class="alert alert-${alert}">
											${message}
									</div>
								</c:if>
								<div class="widget-box table-filter">
									<div class="table-btn-controls">
										<div class="pull-right tableTools-container">
											<div class="dt-buttons btn-overlap btn-group">
												<a flag="info"
												   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
														<c:url var="createNewURL" value="/admin/news/edit"/>
												   title='Adding Post' href=${createNewURL}/>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
												</a>
												<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
														class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Delete Post'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
												</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th><input type="checkbox" id="checkAll"></th>
														<th>Post Name</th>
														<th>Short Description</th>
														<th>Modify</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
															<td>${item.title}</td>
															<td>${item.shortDescription}</td>
															<td>
																<c:url var="editURL" value="/admin/news/edit">
																	<c:param name="id" value="${item.id}"/>
																</c:url>
																<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
																   title="Edit Post" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
												
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>
											<input type="hidden" value="" id="page" name="page"/>
											<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
<%--											<input type="hidden" value="" id="sortName" name="sortName"/>	--%>
<%--											<input type="hidden" value="" id="sortBy" name="sortBy"/>												--%>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</form>	
			</div>
			
	<script>
    $(function () {
    	var totalPages = ${model.totalPage};
    	var visiblePages = ${model.maxPageItem};
    	var currentPage = ${model.page};
     	var limit = 2;
		window.pagObj = $('#pagination').twbsPagination({
			totalPages: totalPages,
			visiblePages: visiblePages,
			startPage: currentPage,
			/* id in input above */
			onPageClick: function (event, page) {
				if (currentPage != page) {
					$('#page').val(page);
					// $('#sortName').val('title');
					// $('#sortBy').val('desc');
					$('#maxPageItem').val(limit);
					$('#formSubmit').submit();
				}
			}
		});
	});

	function warningBeforeDelete() {
		swal({
			title: "Confirm Delete",
			text: "Are you sure to delete ?",
			type: "Warning",
			showCancelButton: true,
			confirmButtonClass: "btn-success",
			cancelButtonClass: "btn-danger",
			confirmButtonText: "YES",
			cancelButtonText: "NO",
		}).then(function(isConfirm) {
			if (isConfirm) {
				var ids = $('tbody input[type=checkbox]:checked').map(function () {
					return $(this).val();
				}).get();
				deleteNew(ids);
			}
		});
	}

	function deleteNew(data) {
		$.ajax({
			url: '${newAPI}',
			type: 'DELETE',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function (result) {
				window.location.href = "${newURL}?page=1&maxPageItem=2&message=delete_success";
			},
			error: function (error) {
				window.location.href = "${newURL}?page=1&maxPageItem=2&message=error_system";
			}
		});
	}
    </script>

</body>
</html>