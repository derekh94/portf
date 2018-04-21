#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <assert.h>
#include "linked.h"
#include "Node.h"

List* createList(){
	//create ref/allocate space
	List* l = (List*)malloc(sizeof(List*));
	//set members
	l->head = NULL;
	l->tail = NULL;
	l->count = NULL;
	//return ref
	return l;
}

void pushFront(int d, List* l){	//adds a node to the front
	//create a node
	Node* n = createNode(d);	//create the node

	//if no nodes in list
	if (isListEmpty(l)){
		l->head = n;	//head is node
		l->tail = n;	//tail is same node
	}
	//if one or more nodes in list
	else{
		l->head->prevNode = n;	//adds node to front, new node is new head
		n->nextNode = l->head;
		l->head = n;
	}
	//increment count
	l->count++;
}

void pushBack(int d, List* l){	//adds a node to the back
	//create a node
	Node* n = createNode(d);

	//if no nodes in list
	if (isListEmpty(l)){
		l->head = n;
		l->tail = n;
	}
	//if one or more nodes in list
	else{
		l->tail->nextNode = n;
		n->prevNode = l->tail;
		l->tail = n;
	}
	//increment count
	l->count++;
}

//pop is like delete
void popFront(List* l){	//deletes the node in the front
	//no items
	assert(!isListEmpty(l));	//assert: asserts something is true, crashes if false

	int d = l->head->value;
	//one item
	if (l->count == 1){
		freeNode(l->head);
		l->head = NULL;
		l->tail = NULL;
	}
	//many items
	else{
		l->head = l->head->nextNode;	//move head back
		freeNode(l->head->prevNode);	//free head's next
		l->head->prevNode = NULL;
	}

	l->count--;
	return d;
}

void popBack(List* l){	//deletes the node in the back
	//no items
	assert(!isListEmpty(l));	//assert: asserts something is true, crashes if false

	int d = l->tail->value;
	//one item
	if (l->count == 1){
		freeNode(l->tail);
		l->head = NULL;
		l->tail = NULL;
	}
	//many items
	else{
		l->tail = l->tail->prevNode;	//move head back
		freeNode(l->tail->nextNode);	//free head's next
		l->tail->nextNode = NULL;
	}

	l->count--;
	return d;
}


void Insert(int d, List* l, int i){	//Insert node to index
	//create node
	Node* n = createNode(d);	//create the node

	//no items
	if (isListEmpty(l)){
		l->head = n;	//head is node
		l->tail = n;	//tail is same node
		l->count++;
	}
	//one or more items
	if (l->count >= i){
		Node* temp;
		temp = l->head;
		//cycle through linked list
		for (int n = 0; n < i; n++){
			temp = temp->nextNode;
		}
		
		n->nextNode = temp;		//link new node to next node
		n->prevNode = temp->prevNode;	//link new node's previous node to previous node
		temp->prevNode->nextNode = n;	//link previous node's next node to new node
		l->count++;
	}
	//less items in list than specified value
	else{
		printf("\nPush operation failed: index out of range\n");
	}

}

void Remove(List* l, int i){	//Delete node from index
	//no items
	assert(!isListEmpty(l));	//assert: asserts something is true, crashes if false

	Node* temp = l->head;
	Node* temp2;
	for (int n = 0; n < i; n++){
		temp = temp->nextNode;
	}
	//one item
	if (l->count == 1){
		freeNode(l->tail);
		l->head = NULL;
		l->tail = NULL;
	}
	//many items
	else{
		temp->prevNode->nextNode = temp->nextNode;	//set prev node's ptr to node after deleted node
		temp->nextNode->prevNode = temp->prevNode;	//set next node's ptr to node before deleted node
		freeNode(temp);	//free node
	}

	l->count--;
	return temp;

}

void displayList(List * l){
	//list is empty
	if (isListEmpty(l)){
		printf("\nList is empty");
	}
	else{
		//create iterato
		Node* it = l->head;
		while (it->nextNode != NULL){
			//print iterator
			displayNode(it);
			printf(", ");
			//move iterator
			it = it->nextNode;
		}
		displayNode(it);
	}
}

int isListEmpty(List* l){
	if (l->count == 0){
		return 1;	//true
	}
	else{
		return 0;	//false
	}
}

void freeList(List* l){
	//create iterator
	Node* it = l->head;
	while (it != NULL){
		l->head = l->head->prevNode;	//move head back
		//print iterator
		freeNode(it);
		//move iterator
		it = it->nextNode;
	}
	
	free(l);
}
