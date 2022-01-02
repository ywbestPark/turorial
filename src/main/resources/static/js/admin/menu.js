var spinners = document.getElementsByClassName("spinner-border");
var CreateButton = $('#btn-menu-create');
var updateButton = $('#btn-menu-update');
var deleteButton = $('#btn-menu-delete');
var buttons = $('button')

function loadingBarShow(){
    for (let index = 0; index < spinners.length; index++) {
        spinners[index].style.visibility = 'visible';
    }
    buttons.attr('disabled', true);
}

function loadingBarHide(){
    for (let index = 0; index < spinners.length; index++) {
        spinners[index].style.visibility = 'hidden';
    }
    buttons.attr('disabled', false);
}

var main = {

   init : function () {
   debugger;

        var _this = this;

        loadingBarHide();

       CreateButton.on('click', function () {
           _this.create(this);
       });

       updateButton.on('click', function () {
           _this.update();
       });

       deleteButton.on('click', function () {
           _this.delete();
       });
   },
   create : function () {
        loadingBarShow()
        var data = {
           parentId : $("#parentId").val(),
           level : $("#level").val(),
           menuOrder : $("#menuOrder").val(),
           menuName : $("#menuName").val(),
           menuPath : $("#menuPath").val(),
           enable : $("#enable").val()
       };

       $.ajax({
           type : 'POST',
           url : '/admin/menu',
           contentType : 'application/json; charset=utf-8',
           data : JSON.stringify(data),
           dataType : 'text'
       }).done(function (data) {
           var jData = JSON.parse(data);
           alert('Create success');
           loadingBarHide();
           window.location.href = '/admin/menu_page';
       }).fail(function (request, status, error) {
           alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
           loadingBarHide();
       });
   },
   update : function () {
          debugger;

          loadingBarShow()

          var data = {
              id : $("#id").val(),
              parentId : $("#parentId").val(),
              level : $("#level").val(),
              menuOrder : $("#menuOrder").val(),
              menuName : $("#menuName").val(),
              menuPath : $("#menuPath").val(),
              enable : $("#enable").val()
          };

          $.ajax({
              type : 'PUT',
              url : '/admin/menu',
              contentType : 'application/json; charset=utf-8',
              data : JSON.stringify(data),
              dataType : 'text'
          }).done(function () {
              alert('update success');
              window.location.href = '/admin/menu_page';
              loadingBarHide();
          }).fail(function (error) {
              alert(JSON.stringify(error));
              loadingBarHide();
          });
   },
   delete : function () {
          var id = $("#id").val();
          debugger;
          $.ajax({
              type : 'DELETE',
              url : '/admin/menu/'+ id,
              dataType : 'text'
          }).done(function () {
          debugger;
              alert('delete sucess');
              window.location.href = '/admin/menu_page';
          }).fail(function (error) {
              alert(JSON.stringify(error));
          });
   }
}

main.init();