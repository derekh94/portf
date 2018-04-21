#ifndef _QUEUE_H
#define _QUEUE_H

#include "Node.h"

class Queue{
private:
	Node* Front;
	Node* Back;
	int size;

public:
	//Adds node to queue
	void enqueue(int d);
	//Removes node from queue
	int dequeue();
	//display queue items
	void displayQueue();
	//returns size
	int getSize();
	//Check if queue is empty
	bool isEmpty();
	//constructor
	Queue();
	//copy constructor
	Queue(const Queue& q);
	//destructor
	~Queue();
};
#endif