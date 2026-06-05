import React, { useState, useEffect } from 'react';
import ConversationItem from './ConversationItem';
import { getConversations } from '../services/conversationService';
import { formatTime } from '../utils/formatTime';
const Conversation = ({ conversations, onSelectConversation }) => {

    // const data = [
    //     {name:'Rey Jhon',time:'just now', message: 'Hey there! Are you finish creating the chat app?', active: true},
    //     {name:'Cherry Ann',time:'12:00', message: 'Hello? Are you available tonight?'},
    //     {name:'Lalaine',time:'yesterday', message: 'I\'m thingking of resigning'},
    //     {name:'Princess',time:'1 day ago', message: 'I found a job :)'},
    //     {name:'Charm',time:'1 day ago', message: 'Can you me some chocolates?'},
    //     {name:'Garen',time:'1 day ago', message: 'I\'m the bravest of all kind'},
    // ]


    return (
        <div className="p-1">
            {conversations.map((item) => (
                <ConversationItem
                    key={item.conversationId}
                    id={item.conversationId}
                    lastMessage={item.lastMessage}
                    lastMessageSenderName={item.lastMessageSenderName}
                    time={formatTime(item.updateAt)}
                    username={item.username}
                    active={true}
                    onSelect={onSelectConversation}
                />
            ))}
        </div>
    );

}

export default Conversation
