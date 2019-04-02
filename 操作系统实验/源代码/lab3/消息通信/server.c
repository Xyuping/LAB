//
//  main.c
//  消息通信 server
//
//  Created by 谢玉萍 on 2018/12/31.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <unistd.h>

#define SERVER_TYPE 0
#define CLIENT_TYPE 1


struct msgbuf1
{
    int mtype;
    char msg[128];
};

int des_Msg(int msg_id)
{
    if(msgctl(msg_id, IPC_RMID, NULL) < 0)
    {
        perror("msgctl");
        return -1;
    }
    return 0;
}

int sendMsg(int msg_id, int sendType, char* msg)
{
    struct msgbuf1 buf;
    buf.mtype = sendType;
    strcpy(buf.msg, msg);
    if(msgsnd(msg_id, (void*)&buf, sizeof(buf.msg), 0) < 0)
    {
        perror("msgsnd");
        return -1;
    }
    return 0;
}

int recvMsg(int msg_id, int recvType, char msg[])
{
    struct msgbuf1 buf;
    buf.mtype = recvType;
    int size=sizeof(buf.msg);
    if(msgrcv(msg_id, (void*)&buf, size, recvType, 0) < 0)
    {
        perror("msgrcv");
        return -1;
    }
    
    strncpy(msg, buf.msg, size);
    msg[size] = 0;
    return 0;
}



int main()
{
    key_t key = ftok("..", 1);
    if(key < 0)
    {
        perror("ftok");
        return -1;
    }
    
    int msgid = msgget(key, IPC_CREAT);
    
    
    if(msgid < 0)
    {
        perror("msgget");
    }
    
    char msg[128] = {0};
    while(1)
    {
        recvMsg(msgid, CLIENT_TYPE, msg);
        if(strcasecmp("quit", msg) == 0)
            break;
        printf("client# %s\n", msg);
        
        printf("Please enter# ");
        fflush(stdout);
        ssize_t s = read(0, msg, sizeof(msg));
        if(s>0)
        {
            msg[s-1]=0;
            sendMsg(msgid, SERVER_TYPE, msg);
            printf("send done, wait recv...\n");
        }
    }
    
    des_Msg(msgid);
    return 0;
}

