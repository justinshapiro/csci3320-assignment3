// Programming Assignment #3 for CSCI3320 - Advanced Programming
// Written by Justin Shapiro

#include <iostream>
#include <stack>

using namespace std;

stack<int> towerA, towerB, towerC;
int move_number = 0;

char getTowerLetter(stack<int>& _tower) {
	if (_tower == towerA)
		return 'A';
	else if (_tower == towerB)
		return 'B';
	else if (_tower == towerC)
		return 'C';

	return 'C';
}

void printTowerDisks(stack<int>& _tower) {
	stack<int> temp_tower;

	for (stack<int> i = _tower; !i.empty(); i.pop())
		temp_tower.push(i.top());

	cout << "[";

	while (!temp_tower.empty()) {
		cout << temp_tower.top();
		temp_tower.pop();

		if (!temp_tower.empty())
			cout << ", ";
	}

	cout << "]" << endl;
}

void printCurrentState(stack<int>& t1, stack<int>& t2, stack<int>& t3) {
	cout << "** Move Number: " << move_number << endl;

	for (char i = 'A'; i < 'D'; i++) {
		cout << i << ": ";
		if (getTowerLetter(t1) == i)
			printTowerDisks(t1);
		else if (getTowerLetter(t2) == i)
			printTowerDisks(t2);
		else
			printTowerDisks(t3);
	}

	cout << endl;
}

void printCurrentState(stack<int>& t1, stack<int>& t2, stack<int>& t3, char to, char from) {
	cout << from << " -> " << to << endl;
	printCurrentState(t1, t2, t3);
}

void moveDisks(int stack_size, stack<int>& source, stack<int>& destination, stack<int>& storage) {
	if (stack_size == 1) {
		destination.push(source.top());
		source.pop();
		
		move_number++;
		char to = getTowerLetter(destination);
		char from = getTowerLetter(source);
		printCurrentState(source, destination, storage, to, from);
	}
	else {
		moveDisks(stack_size - 1, source, storage, destination);

		destination.push(source.top());
		source.pop();

		move_number++;
		char to = getTowerLetter(destination);
		char from = getTowerLetter(source);
		printCurrentState(source, destination, storage, to, from);

		moveDisks(stack_size - 1, storage, destination, source);
	}
}

int main() {
	int num_disks = 0;
	
	cout << "Enter number of discs: ";
	cin >> num_disks;

	for (int i = num_disks; i >= 1; i--)
		towerA.push(i);

	printCurrentState(towerA, towerB, towerC);
	moveDisks(towerA.size(), towerA, towerC, towerB);
	
	cout << "Number of Moves: " << move_number << endl;

	return 0;
}