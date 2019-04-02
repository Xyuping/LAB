//
//  GLOBALS.h
//  实验一
//
//  Created by 谢玉萍 on 2018/11/22.
//  Copyright © 2018年 xyp. All rights reserved.
//

/****************************************************/
/* File: globals.h                                  */
/* Global types and vars for TINY compiler          */
/* must come before other include files             */
/* Compiler Construction: Principles and Practice   */
/* Kenneth C. Louden                                */
/****************************************************/

#ifndef _GLOBALS_H_
#define _GLOBALS_H_

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#ifndef FALSE
#define FALSE 0
#endif

#ifndef TRUE
#define TRUE 1
#endif

#define MAXRESERVED 18

typedef enum
/* book-keeping tokens */
{ENDFILE,ERROR_1,ERROR,ERROR_2,ERROR_3,ERROR_4,ERROR_5,    TK_IF,TK_THEN,TK_ELSE,TK_END,TK_REPEAT,TK_UNTIL,TK_READ,TK_WRITE,
    TK_TRUE,TK_FALSE,TK_OR,TK_AND,TK_NOT,TK_INT,TK_BOOL,TK_STRING,TK_WHILE,TK_DO,
    ID,NUM,STR,
    TK_ASSIGN,TK_EQU,TK_LSS,TK_ADD,TK_SUB,TK_MUL,TK_DIV,TK_LP,TK_RP,SEMI,TK_COMMA,TK_SEMICOLON,TK_GTR,TK_LEQ,TK_GEQ
} TokenType;

extern FILE* source; /* source code text file */
extern FILE* listing; /* listing output text file */
extern FILE* code; /* code text file for TM simulator */
extern int lineno; /* source line number for listing */

/* Error = TRUE prevents further passes if an error occurs */
extern int Error;
#endif

