<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %> 

<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

<div class="container">
	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>시간</th>
                <th>입차 수</th>
                <th>입차 일반 사용자</th>
                <th>입차 <br>월정액 사용자</th>
                <th>출차 수</th>
                <th>출차 <br>일반 사용자</th>
                <th>출차 <br>월정액 사용자</th>
                <th>사용요금</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>0</td>
                <td>12</td>
                <td>3</td>
                <td>9</td>
                <td>10</td>
                <td>5</td>
                <td>5</td>
                <td>100,000</td>
            </tr>
                        <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>110,000</td>
            </tr>
                        <tr>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>120,000</td>
            </tr>
                        <tr>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>133,000</td>
            </tr>
                        <tr>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>160,000</td>
            </tr>
            <tr>
                <td>0</td>
                <td>12</td>
                <td>3</td>
                <td>9</td>
                <td>10</td>
                <td>5</td>
                <td>5</td>
                <td>100,000</td>
            </tr>
                        <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>110,000</td>
            </tr>
                        <tr>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>120,000</td>
            </tr>
                        <tr>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>133,000</td>
            </tr>
                        <tr>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>160,000</td>
            </tr>
                        <tr>
                <td>0</td>
                <td>12</td>
                <td>3</td>
                <td>9</td>
                <td>10</td>
                <td>5</td>
                <td>5</td>
                <td>100,000</td>
            </tr>
                        <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>110,000</td>
            </tr>
                        <tr>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>120,000</td>
            </tr>
                        <tr>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>133,000</td>
            </tr>
                        <tr>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>160,000</td>
            </tr>
                        <tr>
                <td>0</td>
                <td>12</td>
                <td>3</td>
                <td>9</td>
                <td>10</td>
                <td>5</td>
                <td>5</td>
                <td>100,000</td>
            </tr>
                        <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>110,000</td>
            </tr>
                        <tr>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>120,000</td>
            </tr>
                        <tr>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>133,000</td>
            </tr>
                        <tr>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>160,000</td>
            </tr>
                        <tr>
                <td>0</td>
                <td>12</td>
                <td>3</td>
                <td>9</td>
                <td>10</td>
                <td>5</td>
                <td>5</td>
                <td>100,000</td>
            </tr>
                        <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>110,000</td>
            </tr>
                        <tr>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>120,000</td>
            </tr>
                        <tr>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>133,000</td>
            </tr>
                        <tr>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>160,000</td>
            </tr>
                        <tr>
                <td>0</td>
                <td>12</td>
                <td>3</td>
                <td>9</td>
                <td>10</td>
                <td>5</td>
                <td>5</td>
                <td>100,000</td>
            </tr>
                        <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>110,000</td>
            </tr>
                        <tr>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>120,000</td>
            </tr>
                        <tr>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>133,000</td>
            </tr>
                        <tr>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>160,000</td>
            </tr>
                        
        </tbody>
        <tfoot>
            <tr>
                <th>합계</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
                <th>2011/04/25</th>
                <th>$320,800</th>
            </tr>
        </tfoot>
    </table>
</div>
<script>
$(document).ready(function() {
    $('#example').DataTable( {
    	searching: false,
    	info: false,
        "order": [[ 3, "desc" ]]
    } );
} );
</script>
  

<!-- <ul class="pagination justify-content-center">
	<li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
	<li class="page-item"><a class="page-link" href="#">1</a></li>
	<li class="page-item"><a class="page-link" href="#">2</a></li>
	<li class="page-item"><a class="page-link" href="#">3</a></li>
	<li class="page-item"><a class="page-link" href="#">4</a></li>
	<li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
</ul>
   -->
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>