//import fileupload from 'express-fileupload'

const UploadCSV = () => {

  const inpFile = document.getElementById('inpFile')
  //const csvForm = document.getElementById('csvForm')
  const onClick = (e) => {
    e.preventDefault()

    const endpoint = 'upload.php';
    const formData = new FormData();

    console.log(inpFile.files)

    formData.append('inpFile', inpFile.files[0])

    fetch(endpoint, {
      method: 'post',
      body: formData
    })
  }

  return (
    <div style = {{textAlign:"center"}}>
        <h3>Uploading messages.csv and typedefs.csv</h3>
        <form id="csvForm">
            <input type={"file"} accepts={".csv"} id="inpFile" name="inpFile"/><br></br>
            <button type = 'submit' onClick={onClick}> Import CSV </button>
            
        </form>
    </div>
  )
}



// csvForm.addEventListener('submit', e => {

//   e.preventDefault();



//   const endpoint = 'upload.php'
//   const formData = new FormData()
  

//   console.log(inpFile.files)

//   formData.append('inpFile', inpFile.files[0])

//   fetch(endpoint, {
//     method: 'POST',
//     body: formData
//   }).catch(console.error);

//  })

// document.querySelector('#fileUpload').addEventListener('change', event => {
//   handleImageUpload(event)
// })

// app.use(
//   fileupload(),
// )

// app.post('/saveFile', (req, res) => {
//   const fileName = req.files.myFile.name
//   const path = __dirname + '/messageCSV/' + fileName

//   messageCSV.mv(path, (error) => {
//     if (error) {
//       console.error(error)
//       res.writeHead(500, {
//         'Content-Type': 'application/json'
//       })
//       res.end(JSON.stringify({ status: 'error', message: error }))
//       return
//     }

//     res.writeHead(200, {
//       'Content-Type': 'application/json'
//     })
//     res.end(JSON.stringify({ status: 'success', path: '/messageCSV' + fileName }))
//   })
// })
export default UploadCSV