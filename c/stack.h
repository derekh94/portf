#ifndef _STACK_H
#define _STACK_H
#include "Node.h"

typedef struct stack{
	Node* head;
	//Node* tail;
	int count;
}Stack;
//push and pop
//adds to top, removes top item
//creates an empty linked list
Stack* createStack();
//Moves node up
void pushStack(int d, Stack* s);
//Moves node to Top
void popStack(Stack* s);
//Displays list content
void displayStack(Stack * s);
//Check if list is empty
int isStackEmpty(Stack* s);
//frees all nodes and the list
void freeStack(Stack* s);


#endif
