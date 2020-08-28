<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/header.jsp" %> 
 <div class="container-fluid">
	  <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">차량조회</h1>          
          </div>

   <div class="row">

            <div class="col-xl-12 col-md-12 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">조회</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                    
                      <form action="logdetail.do" method="post" >
                      	기간  : <input type="text" id="FDate" name="FDate" size=17 maxlength=17> ~ <input type="text" id="LDate"  name="LDate" size=17 maxlength=17> 
                      	  차량번호 <input type="text" name="cnum" size=10 maxlength=8>
                      	  <input type="submit" value="검색">
                      
                      </form>
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i> 
                    </div>
                  </div>
                </div>
              </div>
            </div>
</div>


     


      
            <!-- Pending Requests Card Example -->
          

          <!-- Content Row -->
          <div class="row">
          
            <!-- Content Column -->
            <div class="col-lg-12 mb-12">

              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">실시간 조회</h6>
                </div>
                <div class="card-body">
               <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">No.</th>
      <th scope="col">입차시간</th>
      <th scope="col">차량번호</th>
      <th scope="col">사용금액</th>
      <th scope="col">여부</th>
      <th scope="col">월정액여부</th>
      <th scope="col">구분</th>
      <th scope="col">차량이미지</th>
    </tr>
  </thead>
  <tbody>
    
	<c:forEach var="arr" items="${list}">
<tr>
      <th scope="row">${arr.idx}</th>
	<td>${arr.cnum}</td>
	<td>${arr.inTime}</td>  	
<%--     <td>${arr.pay}</td> 
 --%> 	
 	<td></td>
    <td>${arr.cpNum}</td>
   <td>${arr.monthNum}</td>
   <td></td>
  <td><button type="button" class="btn btn-primary" data-toggle="modal"  data-idx="${arr.idx}"data-cimg="${arr.cImg}" data-target="#carModal"> 차량 사진 </button></td>
</tr>	
	</c:forEach> 
  
  </tbody>
</table>
                       
               </div>
              </div>

              <!-- Approach -->
               <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Development Approach</h6>
                </div>
                <div class="card-body">
                  <p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce CSS bloat and poor page performance. Custom CSS classes are used to create custom components and custom utility classes.</p>
                  <p class="mb-0">Before working with this theme, you should become familiar with the Bootstrap framework, especially the utility classes.</p>
                </div>
              </div>

            </div>
          </div>
        </div>
        <!-- /.container-fluid -->
      </div>







<%@ include file="/WEB-INF/views/include/footer.jsp" %> 

<script type="text/javascript">

$(function() {
  

	    $("#FDate").datetimepicker(
	    		
	    
	    
	    );
	    $("#LDate").datetimepicker();


	});

	</script>