#include "Node.h"
#include <iostream>
using namespace std;

Node::Node(){
	value = NULL;
	nextNode = prevNode = NULL;

}
Node::Node(int p){
	value = p;
	nextNode = prevNode = NULL;

}

void Node::displayNode(){
	cout << value;
}