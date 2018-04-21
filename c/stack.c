#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <assert.h>
#include "stack.h"
#include "Node.h"

Stack* createStack(){
	//create ref/allocate space
	Stack* s = (Stack*)malloc(sizeof(Stack*));
	//set members
	s->head = NULL;		//top
	//s->tail = NULL;	//bottom
	s->count = NULL;
	//return ref
	return s;
}

void pushStack(int d, Stack* s){
	//create a node
	Node* n = createNode(d);

	//if no nodes in list
	if (isStackEmpty(s)){
		s->head = n;
		//s->tail = n;
	}
	//if one or more nodes in list
	else{
		s->head->prevNode = n;
		n->nextNode = s->head;
		s->head = n;
	}
	//increment count
	s->count++;
}

//pop is like delete
void popStack(Stack* s){
	//no items
	assert(!isStackEmpty(s));	//assert: asserts something is true, crashes if false

	int d = s->head->value;
	//one item
	if (s->count == 1){
		freeNode(s->head);
		s->head = NULL;
		//s->tail = NULL;
	}
	//many items
	else{
		s->head = s->head->nextNode;	//move head back
		freeNode(s->head->prevNode);	//free head's next
		s->head->prevNode = NULL;
	}

	s->count--;
	return d;
}

void displayStack(Stack * s){
	//list is empty
	if (isStackEmpty(s)){
		printf("\nList is empty");
	}
	else{
		//create iterator
		Node* it = s->head;
		//move iterator to back as tail is defunct here
		while (it->nextNode != NULL){
			it = it->nextNode;
		}
		//print out
		while (it != NULL){
			//print iterator
			displayNode(it);
			printf(", ");
			//move iterator
			it = it->prevNode;
		}
	}
}

int isStackEmpty(Stack* s){
	if (s->count == 0){
		return 1;	//true
	}
	else{
		return 0;	//false
	}
}

void freeStack(Stack* s){
	//create iterator
	Node* it = s->head;
	while (it != NULL){
		s->head = s->head->prevNode;	//move head back
		//print iterator
		freeNode(it);
		//move iterator
		it = it->nextNode;
	}

	free(s);
}