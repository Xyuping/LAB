//
//  main.c
//  父子进程管道通信
//
//  Created by 谢玉萍 on 2018/11/27.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <limits.h>
#include <sys/types.h>

#define BUFSZ PIPE_BUF

int main ( void )
{
    int fd[2];
    char buf[BUFSZ];
    pid_t id;
    ssize_t size;
    
    if ( (pipe(fd)) == -1 ){
        perror ( "创建管道失败" );
        exit( 1 );
    }
    
    if ( (id = fork()) == -1 ){
        perror ( "创建子进程失败" );
        exit( 1 );
    }
    else if ( id > 0 ){
        close ( fd[0] );
        write (fd[1], "I am father \n", 20 );
        exit (0);
    }
    else {
        close ( fd[1] );
        size = read (fd[0], buf, BUFSZ );
        if ( size < 0 ){
           
            exit( 1 );
        }
        else
            write(STDOUT_FILENO, buf, size);
        exit(0);
    }
    
}
