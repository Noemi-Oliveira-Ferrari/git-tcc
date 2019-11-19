const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const axios = require("axios");

const app = express();

const server = require("http").Server(app);
const io = require("socket.io").listen(server);

const DOMINIO = "http://localhost:8080/";
const porta = 8081;

io.on("connection", function(socket) {

    console.log(`Socket ${socket.id} conectado!`);


    socket.on("testando", dados =>{
        console.log("____"+dados);
    })

    socket.on("solicitarPedido", pedido =>{
        axios({
            method: "POST",
            url: `${DOMINIO}pedidos/solicitar`,
            timeout: 30000,
            data: pedido
        })
        .then(response =>{
            socket.emit(`pedidoSolicitado-${pedido.profissional.idProfissional}`, pedido);
        });            
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

server.listen(porta, ()=>{
    console.log("Node Socket ouvindo a porta "+porta);
});