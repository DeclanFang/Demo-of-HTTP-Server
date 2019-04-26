# Demo-of-HTTP-Server
This is a demo of HTTP server which can request and response to the client to the target address with some operation. 

The server has multiple functions, like encapsulating HTTP protocol version, state code, description into the Response Head and pushing it to clients, where the server can determine the response context by what the statue is. On the other hand, the server can de-encapsulate the request into parts and store different parts in different locations for further use. 

I've also encapsulated the Response and Request which is easier to modulate and improve. 

========================
During the second upload, I've created a function named Dispatcher, which can distinguish the aim of our operation like log in or register, also I have target servlets, and I build a mini frontend to test the performance of the Dispatcher: 

![image](https://github.com/DeclanFang/Demo-of-HTTP-Server/blob/master/DemoPre/Demo01.gif)

========================
For the third update, I reschedule the order of the files to make it classified into the frontend, backend(server) and some others, which makes the project easier to modulate in the future. And in this update, I rebuild the Dispatcher into an XML file, which makes the engineer(me) feel free to make any change without harming the core files. 

The most difficult part is the parsing of the XML file into the information we need (like which URL we aim, what is the name of the servlet and its address), so I extended from the DefaultHandler interface. Because of that, I can easily add other servlets into the server. 
