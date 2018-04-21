#include <iostream>

using namespace std;

void topswap(float arr[], int len){
	float temp = arr[0];
	arr[0] = arr[len];
	arr[len] = temp;
	
}

void heapify(float arr[], int root, int len){
	int L = (root * 2) + 1;
	int R = (root * 2) + 2;

	if (L < len){
		heapify(arr, L, len);
	}
	if (R < len){
		heapify(arr, R, len);
	}
	if (L < len){
		if (arr[root] < arr[L]){
			float temp = arr[root];
			arr[root] = arr[L];
			arr[L] = temp;
		}
	}
	if (R < len){
		if (arr[root] < arr[R]){
			float temp = arr[root];
			arr[root] = arr[R];
			arr[R] = temp;
		}
	}

}

void heapSort(float arr[], int len){
	while (len > 0){
		heapify(arr, 0, len);
		topswap(arr, --len);
	}
}

int main(){

	float arr1[10];
	for (int i = 0; i < 10; i++){
		arr1[i] = rand() / 100;
		cout << arr1[i] << ",";
	}
	cout << endl;

	heapSort(arr1, 10);

	for (int i = 0; i < 10; i++){
		cout << arr1[i] << ",";
	}

	return 0;
}