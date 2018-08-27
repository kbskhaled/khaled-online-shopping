<div class="container">
	<div class="row">

		<!-- display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>


		<!-- display products -->
		<div class="col-md-9">

			<!-- breadcrump component-->
			<div class="row">

				<div class="col-md-12">
					<c:if test="${userClickAllProducts==true}">

						<script>
							window.categoryId = '';
						</script>


						<ol class="breadcrumb">
							<li href="${contextRoot}/home">Home</li>
							<li class="active">All Products</li>
						</ol>
					</c:if>


					<c:if test="${userClickCategoryProducts==true}">

		                <script>
							window.categoryId = '${category.id}';
						</script>
						
						<ol class="breadcrumb">
							<li href="${contextRoot}/home">Home</li>
							<li class="active">All Products</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>


				</div>
			</div>

			<!-- datatables -->
			<div class="row">
				<div class="col-xs-12">

					<table id="productListTable"
						class="table table-strped table-borderd">
						<thead>
							<tr>
							    <th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Quantity available</th>
								<th></th>
							</tr>
						</thead>
						
							<tfoot>
							<tr>
							    <th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Quantity available</th>
								<th></th>
							</tr>
						</tfoot>


					</table>

				</div>


			</div>

		</div>

	</div>



</div>