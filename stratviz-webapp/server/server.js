const io = require('socket.io')(3001, {
    cors: {
        origin: ['http://localhost:3000']
    }
})

// connection = message type
// socket = data object in the message
io.on('connection', socket => {
    // what happens if we get a message of type connection
    console.log(socket.id)
})

io.on('hello', () => {
    // What happens when this type of message is received
})