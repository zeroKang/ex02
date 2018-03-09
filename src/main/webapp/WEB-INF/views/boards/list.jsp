<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>


<%@include file="../includes/header.jsp"%>


<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <!-- before -->
            <!--<div class="panel-heading">Board List Page</div>-->
            <!-- after -->
            <div class="panel-heading">Board List Page
              <button id='regBtn' type="button" class="btn btn-xs pull-right">Register New Board</button>
            </div>

          <!-- /.panel-heading -->
            <div class="panel-body">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>#번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="board">
                    <tr>
                      <td><c:out value="${board.bno}"/></td>
                      <td><a href='/boards/get?bno=<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td>
                      <td><c:out value="${board.writer}"/></td>
                      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${board.regdate}" /></td>
                      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${board.updateDate}" /></td>
                    </tr>
                    </c:forEach>
                </table>

                <!-- Modal  추가 -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            </div>
                            <div class="modal-body">처리가 완료되었습니다.</div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

            </div>
            <!--  end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->

<script type="text/javascript">

    $(document).ready(function(){

      $("#regBtn").on("click", function(){

        self.location ="/board/register";

      });


      var result = '${result}';
        checkModal(result);

        function checkModal(result){

            if(result === ''){
                return;
            }

            if(parseInt(result) > 0 ){
                $(".modal-body").html( "게시글 " + parseInt(result) +" 번이 등록되었습니다.");
            }

            $("#myModal").modal("show");
        }
    });
</script>


<%@include file="../includes/footer.jsp"%>
