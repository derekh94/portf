#ifndef _NODE_H
#define _NODE_H

class Node{
	//functions go in public
public:
	int value;
	Node* nextNode;
	Node* prevNode;
	//display the node
	void displayNode();

	Node();
	Node(int p);
	//variables go in private
private:


};

#endif