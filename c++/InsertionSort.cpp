#include <iostream>
#include <random>
using namespace std;





void insertionSort(float arr[], int len){

	int fwd = 1, back;
	
	float tempArr[arrlen];
	insert 1st item into list
	for (int fwd = 1; fwd < len; fwd++)		//alternate loop choice
	while (fwd < len){		//go forward
		if (arr[fwd] < arr[fwd - 1]){
			back = fwd - 1;
			//for(int b=fwd;arr[b]<arr[b+1] && b >= 0;b--)		//alternate loop choice
			while (arr[back] > arr[back + 1] && back >= 0){		//if out of order, push back until it is in order
				float temp = arr[back + 1];
				arr[back + 1] = arr[back];
				arr[back] = temp;
				back--;
			}
		}
		fwd++;
	}
	
	//loop, insert other items
}

int main(){

	float arr1[10];
	for (int i = 0; i < 10; i++){
		arr1[i] = rand() / 100;
		cout << arr1[i] <<",";
	}
	cout << endl;

	insertionSort(arr1, 10);

	for (int i = 0; i < 10; i++){
		cout << arr1[i] << ",";
	}

	return 0;
}