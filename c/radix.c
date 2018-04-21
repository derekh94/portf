/*
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include "queue.h"

#define NUM_NUM 10

int powi(int x, int y){
	int num;
	num = 1;
	for (int i = 0; i < y; i++){
		num *= x;
	}
	return num;
}

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
			bucketPlace = (num / (powi(10,k))) % 10;
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
		printf("Pass #%d", k+1);
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