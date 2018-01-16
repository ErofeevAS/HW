package com.erofeev.hotel.managers;

import java.util.ArrayList;

import com.erofeev.hotel.api.IEntity;

public abstract class AbstractManager<E> {

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

	public int getMaxId() {
		int maxId = 0;
		ArrayList<E> list = getAll();
		for (E entity : list) {
			if (((IEntity) entity).getID() > maxId) {
				maxId = ((IEntity) entity).getID();
			}
		}
		return maxId;
	}

	public void update(ArrayList newServices) {
		ArrayList<E> entityes = getAll();
		for (int i = 0; i < newServices.size(); i++) {
			boolean containFlag = false;
			IEntity newEntity = (IEntity) newServices.get(i);
			int newID = newEntity.getID();
			for (int j = 0; j < entityes.size(); j++) {
				IEntity entity = (IEntity) entityes.get(j);
				int ID = entity.getID();
				if (ID == newID) {
					entityes.remove(j);
					entityes.add(j, (E) newEntity);
					containFlag = true;
					break;
				}
			}
			if (containFlag == false) {
				entityes.add((E) newEntity);
			}
		}

	}

	public E findExistingEntity(E entity) {
		int index = this.getAll().indexOf(entity);
		if (index == -1) {
			return null;
		} else {
			return this.getAll().get(index);
		}

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
