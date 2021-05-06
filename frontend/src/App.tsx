import React from 'react';
import {Card, Col, Input, Layout, Row, Timeline} from "antd";
import {Content} from "antd/es/layout/layout";
import {uniq} from "ramda";
import {useEffect, useState} from "react";
import {Message} from "./Message";
import 'antd/dist/antd.css';
import './App.scss';
import moment from "moment";
import {ajax} from "rxjs/ajax";

const {Search} = Input;

function App() {
    const [messages, setMessages] = useState<Message[]>([]);
    const [author, setAuthor] = useState("");
    let eventSource: EventSource;

    useEffect(() => {
        eventSource = new EventSource('http://localhost:8080/messages');
        eventSource.onmessage = event => {
            const data: Message = JSON.parse(event.data);
            setMessages(prevState => uniq([...prevState, data]))
        }
        eventSource.onerror = err => console.log(err)
        return () => {
            eventSource.close();
        }
    }, []);


    const onPost = (message: string) => {
        ajax({
            method: 'POST',
            url: "http://localhost:8080/messages",
            body: {
                message,
                author
            },
            headers: {
                'Content-Type': 'application/json',
            },
        }).subscribe();
    }

    const changeUser = (event: React.ChangeEvent<HTMLInputElement>) => {
        setAuthor(event.target.value)
    }

    return (
        <div className="App">
            <Layout>
                <Content className="App-Content">
                    <div className="App-Messages">
                        <Timeline mode="alternate">
                            {messages.map(({message, date, author: mAuthor}, index) =>
                                <Timeline.Item key={`App-Messages-${index}`} dot={false}
                                               position={(author === mAuthor) ? "left" : "right"}>
                                    <Card title={moment(date).format("hh:mm")}>
                                        {message}
                                        {(author !== mAuthor) && <Card.Meta description={mAuthor}/>}
                                    </Card>
                                </Timeline.Item>)}
                        </Timeline>
                    </div>
                    <div className="App-ChatForm">
                        <Row>
                            <Col span={4} offset={10}>
                                <Input placeholder="Your name" onChange={changeUser}/>

                                <Search enterButton="Post" onSearch={onPost}/>
                            </Col>
                        </Row>
                    </div>
                </Content>
            </Layout>
        </div>
    );
}

export default App;
