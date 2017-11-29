package com.erofeev.hotel.managers;

import java.util.ArrayList;

import com.erofeev.hotel.api.IManager;

public abstract class AbstractManager<E> implements IManager {

	private ArrayList<E> entities = new ArrayList<>();

	public ArrayList<E> getEntities() {
		return entities;
	}

	public abstract ArrayList<E> getAll();

	public void setEntities(ArrayList<E> entities) {
		this.entities = entities;
	}

	public abstract void add(E entity);

	public abstract void remove(E entity);

	public abstract E findbyName(String name);

	public E findExistingEntity(E entity) {
		return this.getAll().get(this.getAll().indexOf(entity));
	}

	public String[] read() {
		ArrayList<E> currentEntities = this.getAll();
		String[] strEntities = new String[currentEntities.size()];
		for (int i = 0; i < currentEntities.size(); i++) {
			if (currentEntities.get(i) != null) {
				strEntities[i] = "";
				strEntities[i] += currentEntities.get(i).toString();
			}
		}
		return strEntities;
	}

}
