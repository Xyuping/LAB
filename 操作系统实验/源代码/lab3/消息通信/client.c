//
//  client.c
//  多进程消息队列通信
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
    long mtype;
    char msg[128];
};

int destoryMsgQueue(int msg_id)
{
    if(msgctl(msg_id, IPC_RMID, NULL) < 0)
    {
        perror("msgctl");
        return -1;
    }
    return 0;
}

int sendMsg(int msg_id, int who, char* msg)
{
    struct msgbuf1 buf;
    buf.mtype = who;
    strcpy(buf.msg, msg);
    
    if(msgsnd(msg_id, (void*)&buf, sizeof(buf.msg), 0) < 0)
    {
        perror("msgsnd");
        return -1;
    }
    return 0;
}

int recvMsg(int msg_id, int recvType, char out[])
{
    struct msgbuf1 buf;
    int size=sizeof(buf.msg);
    if(msgrcv(msg_id, (void*)&buf, size, recvType, 0) < 0)
    {
        perror("msgrcv");
        return -1;
    }
    
    strncpy(out, buf.msg, size);
    out[size] = 0;
    return 0;
}


int main(void)
{
    key_t key = ftok("..", 1);
    if(key < 0)
    {
        perror("ftok");
        return -1;
    }
    int msgid = msgget(key, IPC_CREAT);
    
    char msg[128] = {0};
    while(1)
    {
        printf("Please Enter# ");
        fflush(stdout);
        ssize_t s = read(0, msg, sizeof(msg));
        if(s > 0)
        {
            msg[s-1]=0;
            sendMsg(msgid, CLIENT_TYPE, msg);
            if(strcasecmp("quit", msg) == 0)
                break;
            printf("send done, wait recv...\n");
        }
        
        recvMsg(msgid, SERVER_TYPE, msg);
        printf("server# %s\n", msg);
    }
    return 0;
}
