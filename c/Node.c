#include "Node.h"
#include <stdio.h>
#include <malloc.h>

Node* createNode(int d){
	//create ref/allocate space
	Node* nptr = (Node*)malloc(sizeof(Node));
	//set members
	nptr->value = d;
	nptr->nextNode = NULL;
	nptr->prevNode = NULL;

	//return ref
	return nptr;
}

void displayNode(Node* n){
	printf("%d", n->value);
}

void freeNode(Node* n){
	free(n);
}
