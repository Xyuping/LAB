//
//  server_1.c
//  共享内存通信
//
//  Created by 谢玉萍 on 2018/12/31.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#define BUFSZ 128

int main(int argc, char *argv[])
{
    int shmid;
    key_t key;
    char *shmaddr;
    
    key = ftok("../", 1);
    if(key == -1){
        perror("create key error");
    }
    
    shmid = shmget(key, BUFSZ, IPC_CREAT|0777);
    if(shmid < 0){
        perror("shmget error");
        exit(-1);
    }
    
    shmaddr = shmat(shmid, NULL, 0);
    if(shmaddr < 0)
    {
        perror("shmat error");
        _exit(-1);
    }
    
    printf("writing...\n");
    bzero(shmaddr, BUFSZ);
    strcpy(shmaddr, "Hello world\n");
    
    return 0;
}

