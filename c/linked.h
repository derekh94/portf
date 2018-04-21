#ifndef _LINKED_H
#define _LINKED_H
#include "Node.h"

typedef struct List {
	Node* head;
	Node* tail;
	int count;
}List;

List* createList();
//Adds node in front
void pushFront(int d, List* l);
//Adds node in back
void pushBack(int d, List* l);
//Deletes node to front
void popFront(List* l);
//Deletes node to back
void popBack(List* l);
//Insert node to index
void Insert(int d,List* l,int i);
//Delete node from index
void Remove(List* l,int i);
//Displays list content
void displayList(List * l);
//Check if list is empty
int isListEmpty(List* l);
//frees all nodes and the list
void freeList(List* l);


#endif