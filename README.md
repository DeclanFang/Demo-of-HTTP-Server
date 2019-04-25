# Demo-of-HTTP-Server
This is a demo of HTTP server which can request and response to the client to the target address with some operation. 

The server has multiple functions, like encapsulating HTTP protocol version, state code, description into the Response Head and pushing it to clients, where the server can determine the response context by what the statue is. On the other hand, the server can de-encapsulate the request into parts and store different parts in different locations for further use. 

I've also encapsulated the Response and Request which is easier to modulate and improve. 

During the second upload, I've created a function named Dispatcher, which can distinguish the aim of our operation like login or register, also I have target servlets, and I build a mini frontend to test the performance of the Dispatcher: 

![image](https://github.com/Yesi-hoang/TaoBaoTopLine/blob/master/Gif/TaoBaoTopLineGif.gif)
