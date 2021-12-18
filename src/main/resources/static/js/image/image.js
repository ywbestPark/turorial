var main = {

   init : function () {
   debugger;

       var _this = this;

       $('#btn-image-upload').on('click', function () {
           _this.imageUpload();
       });

       $("#btn-image-download").on('click', function () {
           _this.imageDownload();
       });
   },
   imageUpload : function () {
   debugger;
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
           url : '/image/upload',
           dataType : 'text',
           data : formData,
           contentType : false,
           processData : false
       }).done(function (data) {
           var jData = JSON.parse(data);
           alert(jData.message);
           window.location.href = '/image/';
       }).fail(function (request, status, error) {
           alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
           //$("#submitErrorMessage").show();
       });
   },
   excelDownload2 : function () {
          $.ajax({
              type : 'GET',
              url : '/excel/download/',
          }).done(function () {
              alert('다운로드가 완료 되었습니다.');
          }).fail(function (request, status, error) {
              alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
          });
   },

   imageDownload : function () {
       var $preparingFileModal = $("#preparing-file-modal");
       $preparingFileModal.dialog({ modal: true });
       $("#progressbar").progressbar({value: false});
       $.fileDownload("/excel/download/", {
            successCallback: function (url) {
                $preparingFileModal.dialog('close');
            },
            failCallback: function (responseHtml, url) {
                $preparingFileModal.dialog('close');
                $("#error-modal").dialog({ modal: true });
            }
       }); // 버튼의 원래 클릭 이벤트를 중지 시키기 위해 필요합니다.
       return false;
   }
}

main.init();