//translates from C

#include <iostream>
#include <math.h>
#include <time.h>
#include "Queue.h"

using namespace std;



//exponent function
int powi(int x, int y){
	int num;
	num = 1;
	for (int i = 0; i < y; i++){
		num *= x;
	}
	return num;
}

void radixSort(int arr[],int len)
{
	//---actual sort---
	
	int n;
	int k;
	int num;
	int temp = arr[0];
	int digitCount;
	int maxDigit = 0;

	//find max number

	for (int i = 0; i < len; i++){	//Find max # of digits
		digitCount = 0;
		n = arr[i];
		while (n != 0){
			n = n / 10;
			digitCount++;
		}
		if (digitCount > maxDigit){
			maxDigit = digitCount;
		}
	}
	printf("\nMax digits: %d\n", maxDigit);

	//initialize buckets
	Queue* buckets;
	int bucketPlace;
	buckets = new Queue[10];

	
	//For i in k
	for (k = 0; k < maxDigit; k++){
		for (int i2 = 0; i2 < len; i2++){
			num = arr[i2];		//BUG:: num assigned to something funky
			//	get kth digit of digits
			bucketPlace = (num / (powi(10, k))) % 10;
			//	check # into ith bucket
			buckets[bucketPlace].enqueue(num);
		}
		//	Remove in order
		int ii = 0;
		for (int i3 = 0; i3 < 10; i3++){
			while ((!buckets[i3].isEmpty())) {
				arr[ii] = buckets[i3].dequeue();
				ii++;
			}
		}
	}

	delete[] buckets;

}
//Old Code
/*
int main()
{
	int i;
	srand(time(NULL));
	Queue* Kyu = createQueue();

	//generate numbers
	for (i = 0; i < NUM_NUM; i++){
		enqueue(rand(), Kyu);
	}

	//print numbers
	printf("Generate numbers:\n");
	displayQueue(Kyu);

	//Find max # of digits
	int n;
	int k;
	int num;
	Node* temp = Kyu->head;
	int digitCount;
	int maxDigit = 0;
	for (i = 0; i < NUM_NUM; i++){
		digitCount = 0;
		n = temp->value;
		while (n != 0){
			n = n / 10;
			digitCount++;
		}
		if (digitCount > maxDigit){
			maxDigit = digitCount;
		}
		temp = temp->nextNode;
	}
	printf("\nMax digits: %d\n", maxDigit);

	//initialize buckets
	Queue* buckets[10];
	int bucketPlace;
	for (i = 0; i < 10; i++){
		buckets[i] = createQueue();
	}

	//For i in k
	for (k = 0; k < maxDigit; k++){
		while (Kyu->count != 0){
			num = dequeue(Kyu);
			//	get kth digit of digits
			bucketPlace = (num / (powi(10, k))) % 10;
			//	check # into ith bucket
			enqueue(num, buckets[bucketPlace]);
		}
		//	Remove in order
		int tempnum;
		for (i = 0; i < 10; i++){
			while (buckets[i]->count != 0){
				tempnum = dequeue(buckets[i]);
				enqueue(tempnum, Kyu);
			}
		}
		printf("Pass #%d", k + 1);
		printf("\n");
		displayQueue(Kyu);
		printf("\n");
	}


	//print sorted numbers
	printf("\nFinal Product\n");
	displayQueue(Kyu);

	printf("\nPress Enter to continue...");
	getchar();
	return 0;
}
*/

int main()
{
	
	int arr1[50];
	for (int i = 0; i < 50; i++){
		arr1[i] = rand() % 10000;
		cout << arr1[i] << ",";
	}
	cout << endl;
	
	radixSort(arr1, 50);
	
	for (int i = 0; i < 50; i++){
		cout << arr1[i] << ",";
	}
	
	return 0;
}