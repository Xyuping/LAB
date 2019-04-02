//
//  UTIL.c
//  实验一
//
//  Created by 谢玉萍 on 2018/11/22.
//  Copyright © 2018年 xyp. All rights reserved.
//



#include "GLOBALS.H"
#include "UTIL.H"

void printToken( TokenType token, const char* tokenString )
{ switch (token)
    {
        case TK_IF:fprintf(listing,"(TK_IF,then)\n"); break;
        case TK_THEN:fprintf(listing,"(TK_THEN,then)\n"); break;
        case TK_ELSE:fprintf(listing,"(TK_ELSE,else)\n"); break;
        case TK_END:fprintf(listing,"(TK_END,end)\n"); break;
        case TK_REPEAT:fprintf(listing,"(TK_REPEAT,repeat)\n"); break;
        case TK_UNTIL:fprintf(listing,"(TK_UTIL,until)\n"); break;
        case TK_READ:fprintf(listing,"(TK_READ,read)\n"); break;
        case TK_WRITE:fprintf(listing,"(TK_WRITE,write)\n"); break;
        case TK_TRUE:fprintf(listing,"(TK_TRUE,true)\n"); break;
        case TK_FALSE:fprintf(listing,"(TK_FALSE,false)\n"); break;
        case TK_OR:fprintf(listing,"(TK_OR,or)\n"); break;
        case TK_AND:fprintf(listing,"(TK_AND,and)\n"); break;
        case TK_NOT:fprintf(listing,"(TK_NOT,not)\n"); break;
        case TK_INT:fprintf(listing,"(TK_INT,int)\n"); break;
        case TK_STRING:fprintf(listing,"(TK_STRING,string)\n"); break;
        case TK_WHILE:fprintf(listing,"(TK_WHILE,while)\n"); break;
        case TK_DO:fprintf(listing,"(TK_DO,do)\n"); break;
        case TK_BOOL:fprintf(listing,"(TK_BOOL,bool)\n"); break;
        case TK_ASSIGN: fprintf(listing,"(TK_ASSIGN,:=)\n"); break;
        case TK_LSS: fprintf(listing,"(TK_LSS,<)\n"); break;
        case TK_EQU: fprintf(listing,"(TK_EQU,=)\n"); break;
        case TK_LP: fprintf(listing,"(TK_LP,()\n"); break;
        case TK_RP: fprintf(listing,"(TK_RP,))\n"); break;
        case TK_SEMICOLON: fprintf(listing,"(TK_SEMICOLON,;)\n"); break;
        case TK_ADD: fprintf(listing,"(TK_ADD,+)\n"); break;
        case TK_SUB: fprintf(listing,"(TK_SUB,-)\n"); break;
        case TK_MUL: fprintf(listing,"(TK_MUL,*)\n"); break;
        case TK_DIV: fprintf(listing,"(TK_DIV,/)\n"); break;
        case TK_COMMA:fprintf(listing,"(TK_COMMA,,)\n"); break;
        case TK_GTR:fprintf(listing,"(TK_GTR,>)\n"); break;
        case TK_LEQ:fprintf(listing,"(TK_LEQ,<=)\n"); break;
        case TK_GEQ:fprintf(listing,"(TK_GEQ,>=)\n"); break;
        case ENDFILE: fprintf(listing,"(ENDFILE,EOF)\n"); break;
        case NUM:
            fprintf(listing,
                    "(NUM,%s)\n",tokenString);
            break;
        case ID:
            fprintf(listing,
                    "(ID,%s)\n",tokenString);
            break;
        case ERROR_1:
            fprintf(listing,"ERROR: := 缺少 = \n");
        case ERROR_2:
            fprintf(listing, "ERROR: 字符串错误,缺少右'\n");
            break;
        case ERROR_3:
            fprintf(listing, "ERROR: 注释缺少结尾}\n");
            break;
        case ERROR_4:
            fprintf(listing, "ERROR: 注释错误,缺少{\n");
            break;
        case ERROR_5:
            fprintf(listing,"ERROR:输入非法字符%s\n",tokenString);
            break;
        case ERROR:
            fprintf(listing,
                    "ERROR: %s\n",tokenString);
            break;
        case STR:
            fprintf(listing,"(STRING,%s)", tokenString);
            break;
        default: /* should never happen */
            fprintf(listing,"Unknown token: %d\n",token);
    }
}


