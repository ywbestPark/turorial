var spinner = document.getElementById("spinner");
var saveButton = $('#btn-image-save');
var updateButton = $('#btn-image-update');
var buttons = $('button')
var dataUrl;

function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}

var fileReader = new FileReader();
var filterType = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

fileReader.onload = function (event) {
  var image = new Image();

  image.onload=function(){
      document.getElementById("original-Img").src=image.src;
      var canvas=document.createElement("canvas");
      var context=canvas.getContext("2d");
      var MAX_WIDTH = 600;
      var MAX_HEIGHT = 350;
      var width = image.width;
      var height = image.height;

      if (width > height) {
          if (width > MAX_WIDTH) {
                height *= MAX_WIDTH / width;
                width = MAX_WIDTH;
          }
      } else {
          if (height > MAX_HEIGHT) {
                width *= MAX_HEIGHT / height;
                height = MAX_HEIGHT;
          }
      }
      canvas.width=width;
      canvas.height=height;
      context.drawImage(image, 0, 0, image.width, image.height, 0, 0, canvas.width, canvas.height );

      dataUrl = canvas.toDataURL();
      document.getElementById("upload-Preview").src = dataUrl;
  }
  image.src=event.target.result;
};

var loadImageFile = function () {
  var uploadImage = document.getElementById("file");

  //check and retuns the length of uploded file.
  if (uploadImage.files.length === 0) {
    return;
  }

  //Is Used for validate a valid file.
  var uploadFile = document.getElementById("file").files[0];
  if (!filterType.test(uploadFile.type)) {
    alert("Please select a valid image.");
    return;
  }

  fileReader.readAsDataURL(uploadFile);
}

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


        loadingBarShow()

        var form = $('#form')[0];
        var formData = new FormData(form);

//        var formData = new FormData();
//        formData.append("title", $('#title').val());
//        formData.append("description", $('#description').val());
//        formData.append("file", dataURLtoBlob(dataUrl), "compressed.jpg");

debugger;
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
           window.location.href = '/image?page=0&size=9&sort=modifiedDate,desc';
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