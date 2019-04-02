//
//  main.c
//  实验一
//
//  Created by 谢玉萍 on 2018/11/22.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include "GLOBALS.h"
#include "SCAN.h"
int Error = FALSE;
int lineno = 0;
FILE * source;
FILE * listing;
FILE * code;

int main( int argc, char * argv[] )
{
    printf("请输入源文件路径:");
    char pgm[120];
    scanf("%s",pgm);
    source = fopen(pgm,"r");
    if (source==NULL){
        fprintf(stderr,"File %s not found\n",pgm);
        exit(1);
    }
    listing = stdout;
    fprintf(listing,"\nTINY COMPILATION: %s\n",pgm); //
    while (getToken()!=ENDFILE);
    return 0;
}


