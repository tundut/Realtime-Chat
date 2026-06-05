import React, { useState, useEffect } from 'react';
import ConversationItem from './ConversationItem';
import { getConversations } from '../services/conversationService';
import { formatTime } from '../utils/formatTime';
const Conversation = ({ conversations, onSelectConversation, selectedConversationId }) => {

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
                    active={item.conversationId === selectedConversationId}
                    onSelect={onSelectConversation}
                />
            ))}
        </div>
    );

}

export default Conversation
