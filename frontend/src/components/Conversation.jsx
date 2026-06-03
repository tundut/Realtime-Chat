import React, { useState, useEffect } from 'react';
import ConversationItem from './ConversationItem';
import { getConversations } from '../services/conversationService';
import { formatTime } from '../utils/formatTime';
const Conversation = () => {

    // const data = [
    //     {name:'Rey Jhon',time:'just now', message: 'Hey there! Are you finish creating the chat app?', active: true},
    //     {name:'Cherry Ann',time:'12:00', message: 'Hello? Are you available tonight?'},
    //     {name:'Lalaine',time:'yesterday', message: 'I\'m thingking of resigning'},
    //     {name:'Princess',time:'1 day ago', message: 'I found a job :)'},
    //     {name:'Charm',time:'1 day ago', message: 'Can you me some chocolates?'},
    //     {name:'Garen',time:'1 day ago', message: 'I\'m the bravest of all kind'},
    // ]

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await getConversations();
                setData(res.data);
            } catch (err) {
                console.log(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <div className="p-1">
            {
                data.map((item, index) => (
                    <ConversationItem 
                        key={index}
                        message={item.lastMessage}
                        time={formatTime(item.updateAt)} 
                        name={item.username} 
                        active={true}
                    />
                ))
            }
        </div>
    )
}

export default Conversation
