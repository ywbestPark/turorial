var main = {
   init : function () {
   debugger;
       var _this = this;

       $('#btn-save').on('click', function () {
           _this.save();
       });

       $("#btn-update").on('click', function () {
           _this.update();
       });
   },
   save : function () {
   debugger;
       var data = {
           title : $("#title").val(),
           contents : $("#contents").val()
       };

       $.ajax({
           type : 'POST',
           url : '/board/create',
           dataType : 'text',
           contentType : 'application/json; charset=utf-8',
           data : JSON.stringify(data)
       }).done(function () {
           alert('글이 등록되었습니다.');
           window.location.href = '/board/lists';
       }).fail(function (error) {
           alert(JSON.stringify(error));
       });
   },
   update : function () {
          var data = {
              id : $("#id").val(),
              title : $("#title").val(),
              contents : $("#contents").val()
          };

          var id = $("#id").val();

          $.ajax({
              type : 'PUT',
              url : '/board/update/'+ id,
              dataType : 'text',
              contentType : 'application/json; charset=utf-8',
              data : JSON.stringify(data)
          }).done(function () {
              alert('글이 수정되었습니다.');
              window.location.href = '/board/lists';
          }).fail(function (error) {
              alert(JSON.stringify(error));
          });
      },
         delete : function () {
             var id = $("#id").val();

             $.ajax({
                 type : 'DELETE',
                 url : '/api/v1/posts/'+ id,
                 dataType : 'json',
                 contentType : 'application/json; charset=utf-8'
             }).done(function () {
                 alert('글이 삭제되었습니다.');
                 window.location.href = '/';
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
         }
   };

   main.init();