const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");

const app = express();

const server = require("http").Server(app);
const io = require("socket.io").listen(server);

io.on("connection", function(socket) {
    console.log(`Socket ${socket.id} conectado!`);

    socket.on("testando", data =>{
        console.log(data);
    });
});

// var corsOptions = {
//     origin: 'http://localhost:3000/',
//     optionsSuccessStatus: 200
// }

app.use(bodyParser.json());
app.use(cors());
//io.origins(['*']);


app.get("/teste", (res, req) => {
    console.log("teste");
});

server.listen(8081);