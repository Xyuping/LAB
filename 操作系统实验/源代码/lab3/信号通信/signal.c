//
//  main.c
//  信号量通信
//
//  Created by 谢玉萍 on 2018/12/28.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

#ifndef true
#define true 1
#endif

#ifndef false
#define false 0
#endif

int flag = true;

void handler(int sig)
{
    if(SIGALRM == sig)
    {
        printf("%d got signal SIGALRM\n", getpid());
    }
    else
    {
        
        printf("%d got signal SIGQUIT\n", getpid());
        flag = false;
    }
}

int main()
{
    struct sigaction act;
    act.sa_handler = handler;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;
    sigaction(SIGALRM, &act, 0);
    sigaction(SIGQUIT, &act, 0);
    
    printf("father pid:%d\n",getpid());
    
    pid_t pid;
    pid = fork();
    printf("child pid:%d\n",pid);

    if(pid < 0)
    {
        printf("fork() error\n");
    }
    else if(pid > 0)
    {
        sleep(5);
        kill(pid, SIGQUIT);
        exit(0);
    }
     
    while(flag)
    {
        alarm(1);
        pause();
    }
    
    printf("%d Over\n",getpid());
    
    return 0;
}
