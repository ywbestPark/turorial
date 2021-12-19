var spinner = document.getElementById("spinner");
var saveButton = $('#btn-image-save');
var updateButton = $('#btn-image-update');
var buttons = $('button')

function loadingBarShow(){
    spinner.style.visibility = 'visible'; //'visible'
    buttons.attr('disabled', true);
}

function loadingBarHide(){
    spinner.style.visibility = 'hidden'; //'visible'
    buttons.attr('disabled', false);
}

var main = {

   init : function () {
   debugger;

        var _this = this;

        loadingBarHide();

       saveButton.on('click', function () {
           _this.save(this);
       });

       updateButton.on('click', function () {
           _this.update();
       });
   },
   save : function () {
   debugger;

        loadingBarShow()

        var form = $('#form')[0];
        var formData = new FormData(form);

        var isFormDataEmpty= true;
        for (var p of formData) {
           isFormDataEmpty= false;
           break;
        }

       $("#file").val("")

       $.ajax({
           type : 'POST',
           url : '/image/save',
           dataType : 'text',
           data : formData,
           contentType : false,
           processData : false
       }).done(function (data) {
           var jData = JSON.parse(data);
           alert('save success');
           loadingBarHide();
           window.location.href = '/image/';
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
              title : $("#title").val(),
              description : $("#description").val(),
              imagePath : $("#imagePath").val()
          };

          $.ajax({
              type : 'PUT',
              url : '/image',
              dataType : 'text',
              contentType : 'application/json; charset=utf-8',
              data : JSON.stringify(data)
          }).done(function () {
              alert('update success');
              window.location.href = '/image';
              loadingBarHide();
          }).fail(function (error) {
              alert(JSON.stringify(error));
              loadingBarHide();
          });
   }
}

main.init();