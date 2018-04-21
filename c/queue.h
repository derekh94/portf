#ifndef _QUEUE_H
#define _QUEUE_H
#include "Node.h"

typedef struct queue{
	Node* head;
	Node* tail;
	int count;
}Queue;

//enqueue, dequeue
//add on one side
//process from other side

//creates an empty linked list
Queue* createQueue();
//Adds node to queue
void enqueue(int d, Queue* q);	//pushTop
//Removes node from queue
int dequeue(Queue* q);	//popBot
//Displays list content
void displayQueue(Queue * q);
//Check if list is empty
int isQueueEmpty(Queue * q);
//frees all nodes and the list
void freeQueue(Queue * q);


#endif