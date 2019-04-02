//
//  main.c
//  银行家
//
//  Created by 谢玉萍 on 2018/12/28.
//  Copyright © 2018年 xyp. All rights reserved.
//


#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#define SAFE 1
#define UNSAFE 0
#define FILENAME "./test.txt"

int Allocation[25][25], Max[25][25], Available[25];
int alloc[25] = {0};
int resources[25], running[25], safe=UNSAFE;
int pcount = 0, i, j, exec, r, p;

void get_input(){
    printf("\n请输入进程数: ");
    scanf("%d", &p);
    for (i = 0; i < p; i++) {
        running[i] = 1;
        pcount++;
    }
    printf("\n请输入资源数: ");
    scanf("%d", &r);
    printf("\n请输入各类资源数量:");
    for (i = 0; i < r; i++) {
        scanf("%d", &resources[i]);
    }
    printf("\n请输入已分配的资源表:\n");
    for (i = 0; i < p; i++) {
        for(j = 0; j < r; j++) {
            scanf("%d", &Allocation[i][j]);
        }
    }
    printf("\n请输入进程最大需求资源表:\n");
    for (i = 0; i < p; i++) {
        for(j = 0; j < r; j++) {
            scanf("%d", &Max[i][j]);
        }
    }
}

void init_file(){
    char *s=NULL;
    FILE *fp;
    fp=fopen(FILENAME, "w");
    fputs("\n各类资源数量为: ",fp);
    for (i = 0; i < r; i++) {
        fprintf(fp,"\t%d", resources[i]);
    }
    fputs("\n已分配资源表:\n",fp);
    for (i = 0; i < p; i++) {
        for (j = 0; j < r; j++) {
            fprintf(fp,"\t%d", Allocation[i][j]);
        }
        
        fprintf(fp,"\n");
    }
    fputs("\n进程最大需求资源表:\n",fp);
    for (i = 0; i < p; i++) {
        for (j = 0; j < r; j++) {
            fprintf(fp,"\t%d", Max[i][j]);
        }
        
        fprintf(fp,"\n");
    }
    for (i = 0; i < p; i++) {
        for (j = 0; j < r; j++) {
            alloc[j] += Allocation[i][j];
        }
    }
    fputs("\n已分配资源:",fp);
    for (i = 0; i < r; i++) {
        fprintf(fp,"\t%d", alloc[i]);
    }
    
    for (i = 0; i < r; i++) {
        Available[i] = resources[i] - alloc[i];
    }
    
    fputs("\n可利用资源:",fp);
    for (i = 0; i < r; i++) {
        fprintf(fp,"\t%d", Available[i]);
    }
    fprintf(fp,"\n====================================\n");
    fclose(fp);
}

int main()
{
    get_input();
    init_file();
    FILE *fp=NULL;
    fp=fopen(FILENAME, "at");
    while (pcount != 0) {
        safe = UNSAFE;
        for (i = 0; i < p; i++) {
            if (running[i]) {
                exec = 1;
                for (j = 0; j < r; j++) {
                    if (Max[i][j] - Allocation[i][j] > Available[j]) {
                        exec = 0;
                        break;
                    }
                }
                if (exec) {
                    fprintf(fp,"\n进程%d正在运行\n", i + 1);
                    running[i] = 0;
                    pcount--;
                    safe = SAFE;
                    
                    for (j = 0; j < r; j++) {
                        Available[j] += Allocation[i][j];
                    }
                    
                    break;
                }
            }
        }
        if (!safe) {
            fprintf(fp,"\n进程%d不安全\n",i+1);
            break;
        } else {
            fprintf(fp,"\n进程%d安全，释放资源。",i+1);
            fprintf(fp,"\n当前可利用资源情况为:");
            for (i = 0; i < r; i++) {
                fprintf(fp,"\t%d", Available[i]);
            }
            fprintf(fp,"\n====================================\n");
        }
    }
    fclose(fp);
    return 0;
}
