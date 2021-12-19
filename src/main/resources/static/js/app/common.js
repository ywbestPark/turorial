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

       $("#btn-delete").on('click', function () {
           _this.delete();
       });

       $("#btn-json-convert").on('click', function () {
           _this.json_convert();
       });

   },
   save : function () {
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
             debugger;
             $.ajax({
                 type : 'DELETE',
                 url : '/board/delete/'+ id,
                 dataType : 'text'
             }).done(function () {
             debugger;
                 alert('글이 삭제되었습니다.');
                 window.location.href = '/board/lists';
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
         },
         json_convert : function () {
              var jsonString = $("#floatingTextarea1").val();
              debugger;
              $.ajax({
                   type : 'POST',
                   url : '/json/convert',
                   //contentType : 'application/json; charset=utf-8',
                   contentType : 'application/x-www-form-urlencoded; charset=utf-8',
                   data : {
                    "name" : "test",
                    "jsonString" : jsonString
                   },
                   dataType : 'text'
              }).done(function (data) {
              debugger;
                   console.log(data);
                   alert('변환 되었습니다.');
                   $("#floatingTextarea2").empty();
                   $("#floatingTextarea2").append(data);

              }).fail(function (error) {
                   console.log(error);
                   alert(JSON.stringify(error));

              });
         }
   };

   main.init();
