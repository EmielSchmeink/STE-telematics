//import fileupload from 'express-fileupload'

const UploadCSV = () => {
  return (
    <div style = {{textAlign:"center"}}>
        <h3>Uploading messages.csv and typedefs.csv</h3>
        <form>
            <input type={"file"} accepts={".csv"} id="fileUpload"/>
            <button>Import CSV</button>
            
        </form>
    </div>
  )
}

// const handleMessagesCSVUpload = event => {
//   const files = event.target.files
//   const formData = new FormData()
//   formData.append('myFile', files[0])

//   fetch('/saveFile', {
//     method: 'POST',
//     body: formData
//   })
//   .then(response => response.json())
//   .then(data => {
//     console.log(data.path)
//   })
//   .catch(error => {
//     console.error(error)
//   })
// }

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