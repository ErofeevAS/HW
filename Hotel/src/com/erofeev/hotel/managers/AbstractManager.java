package com.erofeev.hotel.managers;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.mylist.MyList;

public abstract class AbstractManager<E> implements IManager {

	private MyList<E> entities = new MyList<>();

	public MyList<E> getEntities() {
		return entities;
	}
	public MyList<E> getAll() {
		return entities;
	}

	public void setEntities(MyList<E> entities) {
		this.entities = entities;
	}

	public boolean add(E entity) {
		boolean flag = false;
		for (int i = 0; i < entities.length(); i++) {
			if ((entities.get(i).equals(entity))) {
				flag = true;
			}
		}
		if (!flag) {
			entities.add(entity);
		}
		return flag;

	}

	public void remove(E entity) {
		entities.remove(entity);

	}

	

	public E findExistingEntity(E entity) {
		E foundEntity = null;
		for (int i = 0; i < entities.length(); i++) {
			if (entities.get(i).equals(entity)) {
				foundEntity = entities.get(i);
			}
		}
		return foundEntity;
	}

	public E findbyName(String name) {
		E foundEntity = null;
		for (int i = 0; i < entities.length(); i++) {
			if ((((IEntity) entities.get(i)).getName()).equals(name)) {
				foundEntity = entities.get(i);
				break;
			}
		}
		
		return foundEntity;
	}

	public String[] read() {
		MyList<E> currentEntities = this.getAll();
		String[] strEntities = new String[currentEntities.length()];
		for (int i = 0; i < currentEntities.length(); i++) {
			if (currentEntities.get(i) != null) {
				strEntities[i] = "";
				strEntities[i] += currentEntities.get(i).toString();
			}
		}
		return strEntities;
	}

}
