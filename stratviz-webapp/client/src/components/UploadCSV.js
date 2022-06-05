//var socket = require('../index');

const fileBox = document.getElementById('fileBox')
//const csvForm = document.getElementById('csvForm')
const UploadCSV = () => {

// var sock = socket;
// var FReader;
// var selectedFile;
// var Name;

// window.addEventListener('load', Ready);

// function Ready(){
//   if(window.File && window.FileReader){
//     document.getElementById('uploadButton').addEventListener('click', StartUpload);
//     document.getElementById('fileBox').addEventListener('change', FileChosen);
//   }
//   else{
//     document.getElementById('csvForm').innerHTML = "Your browser does not Support The File API Please Update Your Browser";
//   }
// }

// function FileChosen(evnt){
//   selectedFile = evnt.target.files[0];
//   document.getElementById('NameBox').value = selectedFile.name;
// }

// function StartUpload(e){
//   e.preventDefault()

//   if(document.getElementById('fileBox').value !==''){
//     FReader = new FileReader();
//     var Content = "<span id = 'NameArea'>Uploading" + selectedFile.name + " as " + Name + "</span>";
//     Content += '<div id="ProgressContainer"><div id="ProgressBar"></div></div><span id="percent">0%</span>';
//     Content += "<span id='Uploaded'> - <span id='MB'>0</span>/" + Math.round(selectedFile.size / 1048576) + "MB</span>";
//     csvForm.innerHTML = Content;
//     FReader.onload = function(evnt){
//       sock.emit('Upload', {'Name' : Name, Data : evnt.target.result });
//     }
//     sock.emit('Start', {'Name' : Name,'Size' : fileBox.size });
//   }
//   else
//   {
//     alert("Please Select A File");
//   }
// }

  const onClick = (e) => {
    e.preventDefault()

    const endpoint = 'upload.php';
    const formData = new FormData();

    console.log(fileBox.files);

    formData.append('fileBox', fileBox.files[0]);

    fetch(endpoint, {
      method: 'post',
      body: formData
    })

    
  }

  return (
    
    <div style = {{textAlign:"center"}}>
        <h3>Uploading messages.csv and typedefs.csv</h3>
        <span id="csvForm">
            <input type={"file"} accept={".csv"} id="fileBox" name="fileBox"/><br></br>
            
            <button type = 'submit' id='uploadButton' onClick={onClick}> Import CSV </button>
            
        </span>
    </div>
  )
}

export default UploadCSV