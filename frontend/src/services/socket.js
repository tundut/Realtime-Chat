import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

let client = null;

export const connectSocket = (token, onMessage, onConversation) => {
    client = new Client({
        webSocketFactory: () =>
            new SockJS("http://localhost:8080/ws"),

        connectHeaders: {
            Authorization: `Bearer ${token}`
        },

        onConnect: () => {
            console.log("Connected");

            client.subscribe("/user/queue/messages", (message) => {
                const data = JSON.parse(message.body);
                onMessage?.(data);
            });

            client.subscribe("/user/queue/conversations", (message) => {
                const data = JSON.parse(message.body);
                onConversation?.(data);
            });
        },

        onStompError: (frame) => {
            console.error("STOMP Error:", frame);
        }
    });

    client.activate();
};

export const sendMessage = (receiver, content) => {
    if (!client || !client.connected) {
        console.log("Socket not connected");
        return;
    }

    client.publish({
        destination: "/app/chat.private",
        body: JSON.stringify({
            receiver,
            content
        })
    });
};

export const disconnectSocket = () => {
    if (client) {
        client.deactivate();
    }
};