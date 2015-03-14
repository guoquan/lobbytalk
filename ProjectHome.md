# Lobby Talk #
Lobby talk online chat room is a java socket implement of online chatting room. In this project, a simple chatting room client and server protocol is defined and implemented to support request and reply between client and server. Architecture used in this project provides interface for other use and make it possible to apply or transform this into a B/S modeled chat room.

---

Issues report in [Issues](http://code.google.com/p/lobbytalk/issues/) is welcome!

Discuss in [Lobbytalk Offical Group](http://groups.google.com/group/lobbytalk) is welcome!

---



---

## Server ##
In the server, a server socket is listening to a fixed port and will notify the thread pool to obtain a thread to serve the client and be responsible to reply.
Once a client connects, a session will be generated and maintained by the server context.
Thread forward the client’s request to the service dispatcher, and the client receives service.

If what the client request have something to do this the context and other sessions, the service notifies the context to update and update other sessions.

For an example, when a broad casting message sent from client and received service, the context knows it and delivers the message to all sessions.

In this implement, each session has a message queue maintaining message for specific user. This costs some space on server but does step up the server.

When a user is suffering a bad networking access. If a message or request is failed to send, the server think it dead. But the client won’t be dropped immediately. The server will ‘excuse’ and hoping the client will recover in 1 minute, but not shown on online list. If the client still not reachable, it’s dropped.

http://sites.google.com/a/guoquan.net/code-lobbytalk/Home/a.PNG?attredirects=0

## Client ##
Now consider the client side. It provides basic public chatting and private talk methods in a simple, user-friendly client interface. A handler thread run behind the GUI and serve user’s action.

An online user list is provided so that user can talk in private. A user filter let users search among all online users with leading characters of someone’s username.

When a broadcast message or a private message of the user’s own comes, sign show on the tab and notify the user check out the new message.

http://sites.google.com/a/guoquan.net/code-lobbytalk/Home/e.PNG?attredirects=0

## Web server ##

For B/S model usage.

B/S is not supported now.