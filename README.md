# Demo-of-HTTP-Server
This is a demo of HTTP server which can request and response to the client to the target address with some operation. 

The server has multiple functions, like encapsulating HTTP protocol version, state code, description into the Response Head and pushing it to clients, where the server can determine the response context by what the statue is. On the other hand, the server can de-encapsulate the request into parts and store different parts in different locations for further use. 

I've also encapsulated the Response and Request which is easier to modulate and improve. 
