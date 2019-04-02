//
//  SCAN.c
//  实验一
//
//  Created by 谢玉萍 on 2018/11/22.
//  Copyright © 2018年 xyp. All rights reserved.
//

#include "GLOBALS.h"
#include "UTIL.h"
#include "SCAN.h"

typedef enum { START,INASSIGN,INCOMMENT,INNUM,INID,DONE,INLSS,INSTR,INGTR }StateType;
// < 进入INLSS状态  ；  > 进入INGTR状态  ；  ‘进入 INSTR 状态
char tokenString[MAXTOKENLEN+1];
#define BUFLEN 256

static char lineBuf[BUFLEN]; /* holds the current line */
static int linepos = 0; /* current position in LineBuf */
static int bufsize = 0; /* current size of buffer string */
static int EOF_flag = FALSE; /* corrects ungetNextChar behavior on EOF */
int count = 0;
int EchoSource = TRUE;
/* getNextChar fetches the next non-blank character
 from lineBuf, reading in a new line if lineBuf is
 exhausted */
static int getNextChar(void)
{ if (!(linepos < bufsize))   // 初始化 linepos = 0， bufsize = 0 //读取下一块
{ lineno++; //下一行
    if (fgets(lineBuf,BUFLEN-1,source)) //从源文件中提取255个字节到lineBuf中,source源文件
    { if (EchoSource)        // EchoSource 初始化为FALSE
        fprintf(listing,"%4d: %s",lineno,lineBuf);
        bufsize = strlen(lineBuf); //新的块的大小
        linepos = 0;           //新读取的代码块的起始位置
        return lineBuf[linepos++];
    }
    else
    {
        
        EOF_flag = TRUE;
        return EOF;
    }
}
else return lineBuf[linepos++]; // 不需要读取下一个块时
}

/* ungetNextChar backtracks one character
 in lineBuf */
static void ungetNextChar(void)
{
   
    if (!EOF_flag) linepos-- ;
    
} //回退一个字符

/* lookup table of reserved words 保留字 */
static struct
{ char* str;
    TokenType tok;
} reservedWords[MAXRESERVED]  //数组
= {{"if",TK_IF},{"then",TK_THEN},{"else",TK_ELSE},{"end",TK_END},
    {"repeat",TK_REPEAT},{"until",TK_UNTIL},{"read",TK_READ},
    {"write",TK_WRITE},{"true",TK_TRUE},{"false",TK_FALSE},{"or",TK_OR},{"and",TK_AND},{"not",TK_NOT},{"int",TK_INT},{"bool",TK_BOOL},{"string",TK_STRING},{"while",TK_WHILE},{"do",TK_DO}};

/*判断是否为保留字*/
static TokenType reservedLookup (char * s)
{ int i;
    for (i=0;i<MAXRESERVED;i++)
        if (!strcmp(s,reservedWords[i].str))//如果是保留字
            return reservedWords[i].tok;//返回保留字类型
    return ID;
}

/****************************************/
/* the primary function of the scanner  */
/****************************************/
/* function getToken returns the
 * next token in source file
 */
TokenType getToken(void) // TokenType 类型定义在globals.hhp中，为enum类型
{  /* index for string into tokenString */
    int tokenStringIndex = 0;
    /* holds current token to be returned */
    TokenType currentToken;
    /* current state - always begins at START */
    StateType state = START;
    /* flag to indicate save to tokenString */
    int save;
    while (state != DONE)
    { int c = getNextChar();
        save = TRUE;
        switch (state)
        { case START:
                if (isdigit(c)) //是否为整型
                    state = INNUM;
                else if (isalpha(c)) // 是否为字母
                    state = INID;
                else if (c == ':'){
                    state = INASSIGN;
                    
                }
                
                else if ((c == ' ') || (c == '\t') || (c == '\n') ||(c == '\v')||(c == '\f') ||(c == '\r'))  //空白字符
                    save = FALSE;
                else if (c == '{')
                { save = FALSE;
                    state = INCOMMENT;
                    count++;
                }
                else if (c == 39)
                { save = FALSE; //不存单引号,只存引号里面的内容
                    state = INSTR;
                }
                
                else if ( c == '>')
                    state = INGTR;
                else if (c == '<')
                    state = INLSS;
                else
                /* special symbols */
                { state = DONE;//其他操作符以及 EOF ERROR
                    switch (c)
                    { case EOF:
                            save = FALSE;
                            currentToken = ENDFILE;
                            break;
                        case ',':
                            currentToken = TK_COMMA;
                            break;
                        case '=':
                            currentToken = TK_EQU;
                            break;
                            
                        case '+':
                            currentToken = TK_ADD;
                            break;
                        case '-':
                            currentToken = TK_SUB;
                            break;
                        case '*':
                            currentToken = TK_MUL;
                            break;
                        case '/':
                            currentToken = TK_DIV;
                            break;
                        case '(':
                            currentToken = TK_LP;
                            break;
                        case ')':
                            currentToken = TK_RP;
                            break;
                        case ';':
                            currentToken = TK_SEMICOLON;
                            break;
                        case '}':
                            currentToken = ERROR_4;
                            break;
                        default:
                            currentToken = ERROR_5;
                            break;
                    }
                }
                break;
            case INCOMMENT:
                save = FALSE;
                if (c== '{' ){
                    count++;
                }else if( c==EOF){
                    state = DONE;
                    lineno--;
                    if (count!=0)
                        currentToken = ERROR_3;
                    ungetNextChar();
                }else if (c == '}'){
                    count--;
                    if(count==0)
                        state = START;
                    
                }
                break;
            case INASSIGN:
                state = DONE;
                if (c == '=')
                    currentToken =  TK_ASSIGN;
                else
                {
                    ungetNextChar();
                    save = FALSE;
                    currentToken = ERROR_1;
                }
                break;
            case INNUM:
                if (!isdigit(c))
                { /* backup in the input */
                    ungetNextChar();
                    save = FALSE;
                    state = DONE;
                    currentToken = NUM;
                }
                break;
            case INLSS:
                if (c=='=') {
                    save = TRUE;
                    state = DONE;
                    currentToken = TK_LEQ;
                }
                else{
                    ungetNextChar();
                    save = FALSE;
                    state = DONE;
                    currentToken = TK_LSS;
                }
                break;
            case INGTR:
                if (c=='=') {
                    save = TRUE;
                    state = DONE;
                    currentToken = TK_GEQ;
                }
                else{
                    ungetNextChar();
                    save = FALSE;
                    state = DONE;
                    currentToken = TK_GTR;
                }
                break;
            case INID:
                if (!isalpha(c))
                { /* backup in the input */
                    ungetNextChar();
                    save = FALSE;
                    state = DONE;
                    currentToken = ID;
                }
                break;
            case  INSTR:
                
                if (c == '\n'|| c==EOF)
                {
                    save = FALSE;
                    state = DONE;
                    currentToken = ERROR_2; //缺少’错误
                    if(c==EOF){
                        ungetNextChar();
                    }
                }
                else if (c == 39){  // 39是 ' 的 ASCII 值
                    save = FALSE;
                    state = DONE;
                    currentToken = STR;
                }else{
                    save = TRUE;
                }
                break;
                
            case DONE:
            default: /* should never happen */
                
                fprintf(listing,"Scanner Bug: state= %d\n",state);
                state = DONE;
                currentToken = ERROR;
                break;
        }
        if ((save) && (tokenStringIndex <= MAXTOKENLEN))
            tokenString[tokenStringIndex++] = (char) c;
        if (state == DONE)
        { tokenString[tokenStringIndex] = '\0';
            if (currentToken == ID)
                currentToken = reservedLookup(tokenString);
        }
    }
        fprintf(listing,"\t%d: ",lineno);
        printToken(currentToken,tokenString);

    return currentToken;
} /* end getToken */

