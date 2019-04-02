//
//  client_1.c
//  共享内存通信
//
//  Created by 谢玉萍 on 2018/12/31.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#define BUFSZ 128

int main(int argc, char *argv[])
{
    int shmid;
    int ret;
    key_t key;
    char *shmaddr;
    
    key = ftok("../", 1);
    if(key == -1)
    {
        perror("create key error");
    }
    
    system("ipcs -m");
    
    shmid = shmget(key, BUFSZ, IPC_CREAT|0777);
    if(shmid < 0)
    {
        perror("shmget error");
        exit(-1);
    }
    
    shmaddr = shmat(shmid, NULL, 0);
    if(shmaddr < 0)
    {
        perror("shmat error");
        exit(-1);
    }
    
    printf("（client）read： [%s]\n", shmaddr);
    
    
    ret = shmdt(shmaddr);
    if(ret < 0){
        perror("shmdt error");
        exit(1);
    }
    
    shmctl(shmid, IPC_RMID, NULL);
    
    system("ipcs -m");
    
    return 0;
}

