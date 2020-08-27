<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header1.jsp" %> 
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">실시간 현황 조회</h1>
            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-4 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">총 주자수</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-4 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">일반 </div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-4 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">월정액</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                        </div>
                        <div class="col">
                          <div class="progress progress-sm mr-2">
                            <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
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
      <!-- End of Main Content -->
      
      <!-- 모달창 -->
  
      
        <div class="modal fade" id="carModal" tabindex="-1" role="dialog" aria-labelledby="carModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="carModalLabel">차량이미지</h4>
      </div>
      <div class="modal-body">     
 	<table>
 		<form action="imgupdate.do" enctype="multipart/form-data" method="post">
 		<tr>
 		<td><input type="text" name="idx" id="idx" value="" readonly="readonly"/></td></tr>
 		<tr><td><input type="hidden" name="cimg" id="cimg" value=""></td>	<tr>
 			
        <tr>
        <td>
 		<img id="modalimg" src="" >

        </td></tr>
        <tr>
        <td><input type="file" name="fileName"></td>
 		</tr>
	<img alt="" src=""> 		
 		
 	</table>
      <div class="modal-footer">
      	<input type="submit" value="수정">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </form>
      </div>
    </div>
  </div></div>
  </div>
      
      
      <!-- 모달창 -->
      
      <script>
	var LOGIDX="";
	var CIMG="";
	var IMGSRC="";
	$(document).ready(function() {
		$('#myModal').on('show.bs.modal', function(event) {   
			LOGIDX=$(event.relatedTarget).data('idx');
			CIMG=$(event.relatedTarget).data('cimg');
			var modal=$(this);
			$(".modal-body #idx ").val(LOGIDX);
			$(".modal-body #cimg ").val(CIMG);	
			$(".modal-body #modalimg ").attr("src","/ParkingManage/img/"+CIMG );
					});		
	});
	
</script>
      
      
      
      
      
      
      
      
      
      
      
      
      
 <%@ include file="/WEB-INF/views/include/footer1.jsp" %> 