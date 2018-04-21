#include <iostream>
#include <cassert>
#include "Queue.h"

using namespace std;

//constructor
Queue::Queue()
{
	Front = Back = NULL;
	size = 0;
}

Queue::Queue(const Queue& q)
{
	//create iterator
	Node* it = q.Back;
	while (it != NULL){
		//create node with same value as l
		enqueue(it->value);
		it = it->nextNode;
	}
}
//Adds node to queue
void Queue::enqueue(int d){	//pushTop
	//create a node
	Node* n = new Node;	//create the node
	n->value = d;
	//if no nodes in list
	if (isEmpty()){
		Front = n;	//head is node
		Back = n;	//tail is same node
	}
	//if one or more nodes in list
	else{
		//adds node to front, new node is new head
		Back->nextNode = n;
		n->prevNode = Back;
		Back = n;
	}
	//increment count
	size++;
}

//Removes node from queue
int Queue::dequeue(){	//popBot
	//no items
	assert(!isEmpty());	//assert: asserts something is true, crashes if false

	int d = Front->value;
	Node* temp = Back;
	//one item
	if (size == 1){
		delete(Front);
		Front = NULL;
		Back = NULL;
	}
	//many items
	else{
		Front = Front->nextNode;	//move head back
		delete(Front->prevNode);	//free head's next
		Front->prevNode = NULL;
	}

	size--;
	return d;
}

//display queue items
void Queue::displayQueue()
{
	Node* it;
	it = Front;
	while (it != NULL){
		it->displayNode();
		//if statement prevents comma after last number
		if (it->nextNode != NULL){
			cout << ",";
		}
		it = it->nextNode;
	}
	cout << endl;
}

//returns size
int Queue::getSize()
{
	return size;
}

//Check if list is empty
bool Queue::isEmpty()
{
	return (size == 0) ? true : false;
}

//destructor
Queue::~Queue()
{
	//frees all nodes and the list
	//create iterator
	while (!isEmpty()){
		dequeue();
	}
}