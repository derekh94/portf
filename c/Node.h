#ifndef _NODE_H
#define _NODE_H


typedef struct node{
	int value;
	struct node* nextNode;
	struct node* prevNode;
} Node;

//creates a node with data d
Node* createNode(int d);
//display the node
void displayNode(Node* n);
//free node from memory
void freeNode(Node* n);

#endif