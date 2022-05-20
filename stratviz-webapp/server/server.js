const io = require('socket.io')(3001, {
    cors: {
        origin: ['http://localhost:3000'] // This relates to sockets trying to connect from other origins than the active process
    }
})

// connection = message type
// socket = data object in the message
io.on('connection', socket => {
    // what happens if we get a message of type connection
    console.log(socket.id)

    // What happens when a can message comes in
    socket.on('canMessage', (car, timestamp, message) => {
        // When canMessage comes in, log its information to the console.
        console.log(`Message received from ${car} at ${timestamp}: ${message}`)
    })
})