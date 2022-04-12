# [RFC865 - Quote of the Day Protocol](https://datatracker.ietf.org/doc/html/rfc865)

---

This RFC specifies a standard for the ARPA Internet community. Hosts on the ARPA Internet that choose to implement a Character Generator Protocol are expected to adopt and implement this standard.

A useful debugging and measurement tool is a character generator service. A character generator service simply sends data without regard to the input.

TCP Based Character Generator Service

One character generator service is defined as a connection based application on TCP. A server listens for TCP connections on TCP port

19. Once a connection is established a stream of data is sent out the connection (and any data received is thrown away). This continues until the calling user terminates the connection.

It is fairly likely that users of this service will abruptly decide that they have had enough and abort the TCP connection, instead of carefully closing it. The service should be prepared for either the carfull close or the rude abort.

The data flow over the connectionThis RFC specifies a standard for the ARPA Internet community. Hosts on the ARPA Internet that choose to implement a Quote of the Day Protocol are expected to adopt and implement this standard.

A useful debugging and measurement tool is a quote of the day service. A quote of the day service simply sends a short message without regard to the input.

TCP Based Character Generator Service

One quote of the day service is defined as a connection based application on TCP. A server listens for TCP connections on TCP port

17. Once a connection is established a short message is sent out the connection (and any data received is thrown away). The service closes the connection after sending the quote.

UDP Based Character Generator Service

Another quote of the day service is defined as a datagram based application on UDP. A server listens for UDP datagrams on UDP port

17. When a datagram is received, an answering datagram is sent containing a quote (the data in the received datagram is ignored).

Quote Syntax

There is no specific syntax for the quote. It is recommended that it be limited to the ASCII printing characters, space, carriage return, and line feed. The quote may be just one or up to several lines, but it should be less than 512 characters.

<br />

## Example

---

```shell
Keep away from people who try to belittle your ambitions. Small people always do that, but the really great make you feel that you, too, can become great. -- Mark Twain
```

<br />

## Implementation

---

<br />
