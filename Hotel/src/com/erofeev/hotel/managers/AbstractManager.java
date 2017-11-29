package com.erofeev.hotel.managers;

import java.util.ArrayList;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.api.IManager;


public abstract class AbstractManager<E> implements IManager {

	private ArrayList<E> entities = new ArrayList<>();

	public ArrayList<E> getEntities() {
		return entities;
	}
	public ArrayList<E> getAll() {
		return entities;
	}

	public void setEntities(ArrayList<E> entities) {
		this.entities = entities;
	}

	public boolean add(E entity) {		
		return !entities.add(entity);

	}

	public void remove(E entity) {
		entities.remove(entity);

	}

	

	public E findExistingEntity(E entity) {
		E foundEntity = null;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).equals(entity)) {
				foundEntity = entities.get(i);
			}
		}
		return foundEntity;
	}

	public E findbyName(String name) {
		E foundEntity = null;
		for (int i = 0; i < entities.size(); i++) {
			if ((((IEntity) entities.get(i)).getName()).equals(name)) {
				foundEntity = entities.get(i);
				break;
			}
		}
		
		return foundEntity;
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
