//
//  r_w.c
//  读者优先
//
//  Created by 谢玉萍 on 2018/11/27.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

pthread_mutex_t rc_mutex;
pthread_mutex_t rw_mutex;

pthread_t ntid;
int readerCount=0;

void reading(int id){
    printf("Thread %d reader start reading\n",id);
    sleep(3);
}
void writing(int id){
    printf("Thread %d writer start writing\n",id);
    sleep(3);
}

void *reader(void *ptr){
    pthread_t tid = pthread_self();
    printf("Thread %d reader is waiting\n",(unsigned int)tid);
    pthread_mutex_lock(&rc_mutex);
    if (readerCount==0){
        
        pthread_mutex_lock(&rw_mutex);
    }
    readerCount++;
    pthread_mutex_unlock(&rc_mutex);
    reading(tid);
    printf("Thread %d reader end reading\n",(unsigned int)tid);
    
    pthread_mutex_lock(&rc_mutex);
    readerCount--;
    if (readerCount==0) {
        pthread_mutex_unlock(&rw_mutex);
        
    }
    pthread_mutex_unlock(&rc_mutex);
    pthread_exit(0);
}
void *writer(void *ptr){
    pthread_t tid = pthread_self();
    printf("Thread %d writer is waiting\n",(unsigned int)tid);
    
    pthread_mutex_lock(&rw_mutex);
    
    writing(tid);
    printf("Thread %d writer end writing\n",(unsigned int)tid);
    pthread_mutex_unlock(&rw_mutex);
    pthread_exit(0);
}

int main(int argc, const char * argv[]) {
    pthread_mutex_init(&rw_mutex,0);
    pthread_mutex_init(&rc_mutex,0);
    pthread_create(&ntid,NULL,reader,NULL);
    pthread_create(&ntid,NULL,reader,NULL);
    pthread_create(&ntid,NULL,writer,NULL);
    pthread_create(&ntid,NULL,writer,NULL);
    pthread_create(&ntid,NULL,reader,NULL);
    pthread_create(&ntid,NULL,reader,NULL);
    pthread_create(&ntid,NULL,reader,NULL);
    pthread_create(&ntid,NULL,writer,NULL);
    pthread_exit((void*)pthread_self());
}

