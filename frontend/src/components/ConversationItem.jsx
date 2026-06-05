import avatar from '../assets/avatar.png'
import React from 'react'

const ConversationItem = ({id, active, time, username, lastMessage, lastMessageSenderName, onSelect}) => {
    const _class = active ? 'bg-gray-200' : 'bg-white';
    return (
        <div 
            onClick={() => {
                onSelect(id);
            }}
        >
            <div className={'conversation-item p-1 dark:bg-gray-700 hover:bg-gray-500 m-1 rounded-md ' + _class}>
                <div className={'flex items-center p-2  cursor-pointer  '}>
                    <div className="w-7 h-7 m-1">
                        <img className="rounded-full" src={ avatar } alt="avatar"/>
                    </div>
                    <div className="flex-grow p-2">
                        <div className="flex justify-between text-md ">
                            <div className="text-sm font-medium text-gray-700 dark:text-gray-200">{username}</div>
                            <div className="text-xs text-gray-400 dark:text-gray-300">{time}</div>
                        </div>
                        <div className="text-sm text-gray-500 dark:text-gray-400  w-40 truncate">
                            {
                                localStorage.getItem('username') === lastMessageSenderName 
                                ? `You: ${lastMessage}`
                                : `${lastMessage}`
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ConversationItem
