<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../includes/header.jsp"%>
<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Register</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading">Board Register</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form">
          <div class="form-group">
            <label>Bno</label>
            <input class="form-control" name='bno'
                   value='<c:out value="${board.bno }"/>' readonly="readonly">
          </div>

          <div class="form-group">
            <label>Title</label>
            <input class="form-control" name='title'
                   value='<c:out value="${board.title }"/>' readonly="readonly">
          </div>

          <div class="form-group">
            <label>Text area</label>
            <textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}"/></textarea>
          </div>

          <div class="form-group">
            <label>Writer</label>
            <input class="form-control" name='writer'
                   value='<c:out value="${board.writer }"/>' readonly="readonly">
          </div>
          <button class="btn btn-default">Modify</button>
          <button class="btn btn-default">List</button>
        </form>

      </div>
      <!--  end panel-body -->
    </div>
    <!-- end panel -->
  </div>
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>


