#include <iostream>

using namespace std;


//split list
//[4,3,2,5,8]


int split(float arr[], int start, int end){
	float pivot = arr[end];
	int temp = start;
	for (int i = start; i < end; i++){
		if (arr[i] <= pivot){
			float tempF = arr[temp];
			arr[temp] = arr[i];
			arr[i] = tempF;
			temp++;
		}
	}
	float tempF = arr[temp];
	arr[temp] = arr[end];
	arr[end] = tempF;


	return temp;
}

void quicksort(float arr[],int start, int end){

	//item in middle becomes pivot
	int midPoint = (end / 2) + start;
	float pivot = arr[midPoint];
	////things less than pivot go left

	//splitarr1 = 0 to midpoint-1 
	//splitarr2 = midpoint to end
	
	//put into array
	for (i = 0; i < end; i++){
		if (arr[i] > pivot){
			temp
		}
	}
	if (start < end){
		int p = split(arr, start, end);
		quicksort(arr, start, p - 1);
		quicksort(arr, p + 1, end);
	}

int main(){
	
	float arr1[10];
	for (int i = 0; i < 10; i++){
		arr1[i] = rand() / 100;
		cout << arr1[i] <<",";
	}
	cout << endl;
	
	quicksort(arr1, 0,9);
	
	for (int i = 0; i < 10; i++){
		cout << arr1[i] << ",";
	}
	
	return 0;
}