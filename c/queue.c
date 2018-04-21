#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <assert.h>
#include "queue.h"
#include "Node.h"

//creates an empty linked list
Queue* createQueue(int d){
	//create ref/allocate space
	Node* nptr = (Node*)malloc(sizeof(Node));
	//set members
	nptr->value = d;
	nptr->nextNode = NULL;
	nptr->prevNode = NULL;

	//return ref
	return nptr;
}

//Adds node to queue
void enqueue(int d, Queue* q){	//pushTop
	//create a node
	Node* n = createNode(d);	//create the node

	//if no nodes in list
	if (isQueueEmpty(q)){
		q->head = n;	//head is node
		q->tail = n;	//tail is same node
	}
	//if one or more nodes in list
	else{
		q->tail->nextNode = n;	//adds node to front, new node is new head
		n->prevNode = q->tail;
		q->tail = n;
	}
	//increment count
	q->count++;
}

//Removes node from queue
int dequeue(Queue* q){	//popBot
	//no items
	assert(!isQueueEmpty(q));	//assert: asserts something is true, crashes if false

	int d = q->head->value;
	Node* temp = q->tail;
	//one item
	if (q->count == 1){
		freeNode(q->head);
		q->head = NULL;
		q->tail = NULL;
	}
	//many items
	else{
		q->head = q->head->nextNode;	//move head back
		freeNode(q->head->prevNode);	//free head's next
		q->head->prevNode = NULL;
	}

	q->count--;
	return d;
}

//Displays list content
void displayQueue(Queue * q){
//list is empty
	if (isQueueEmpty(q)){
		printf("\nList is empty");
	}
	else{
		//create iterato
		Node* it = q->head;
		while (it->nextNode != q->tail->nextNode){
			//print iterator
			displayNode(it);
			printf(", ");
			//move iterator
			it = it->nextNode;
		}
		displayNode(it);
	}
}

//Check if list is empty
int isQueueEmpty(Queue * q){
	if (q->count == 0){
		return 1;	//true
	}
	else{
		return 0;	//false
	}
}

//frees all nodes and the list
void freeQueue(Queue * q){
	//create iterator
	Node* it = q->head;
	while (it != NULL){
		q->head = q->head->prevNode;	//move head back
		//print iterator
		freeNode(it);
		//move iterator
		it = it->nextNode;
	}

	free(q);
}