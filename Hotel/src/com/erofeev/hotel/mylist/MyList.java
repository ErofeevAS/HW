package com.erofeev.hotel.mylist;

public class MyList<E> {

	private E[] myList;

	public MyList() {
		myList = (E[]) new Object[100];
	}

	private int counter = 0;

	public void incCounter() {
		counter++;
	}

	public void dicreaseCounter() {
		counter--;
	}

	public int getCounter() {
		return counter;
	}

	public E[] getMyList() {
		return myList;
	}

	public void add(E obj) {
		myList[getCounter()] = obj;
		incCounter();
	}

	public E get(int i) {
		return myList[i];
	}

	public int length() {
		int index = 0;
		for (int i = 0; i < getMyList().length; i++) {
			if (getMyList()[i] == null) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void remove(E obj) {
		boolean flag = true;
		for (int i = 0; i < getMyList().length; i++) {
			if (getMyList()[i] == null) {
				break;
			}
			if (flag) {
				getMyList()[i] = getMyList()[i];
				if (getMyList()[i].equals(obj)) {
					dicreaseCounter();
					flag = false;
					if (!flag) {
						getMyList()[i] = getMyList()[i + 1];
					}
				}
			} else {
				getMyList()[i] = getMyList()[i + 1];
			}
		}
	}

	public int find(E obj) {
		int result = 0;
		if (obj.equals(null)) {
			System.out.println("wrong argument");
		} else {
			for (int i = 0; i < getMyList().length; i++) {
				if ((getMyList()[i]) == (null)) {
					break;
				}
				if ((getMyList()[i]).equals(obj)) {
					result = i;
					break;
				} else {
					result = -1;
				}
			}
		}
		return result;
	}

}
