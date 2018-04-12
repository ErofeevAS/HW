package com.erofeev.hotel.managers;

import java.util.ArrayList;
import java.util.List;

import com.erofeev.hotel.api.entity.IEntity;

public abstract class AbstractManager<E> {

	private List<E> entities = new ArrayList<>();

	public abstract List<E> getAll();

	public abstract void add(E entity);

	public abstract void remove(E entity);

	public abstract E findbyName(String name);



	public void setEntities(List<E> entities) {
		this.entities = entities;
	}

	public int getMaxId() {
		int maxId = 0;
		List<E> list = getAll();
		for (E entity : list) {
			if (((IEntity) entity).getId() > maxId) {
				maxId = ((IEntity) entity).getId();
			}
		}
		return maxId;
	}

	public void update(List newServices) {
		List<E> entityes = getAll();
		for (int i = 0; i < newServices.size(); i++) {
			boolean containFlag = false;
			IEntity newEntity = (IEntity) newServices.get(i);
			int newID = newEntity.getId();
			for (int j = 0; j < entityes.size(); j++) {
				IEntity entity = (IEntity) entityes.get(j);
				int ID = entity.getId();
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

	/*
	 * public String[] read() { List<E> currentEntities = this.getAll();
	 * String[] strEntities = new String[currentEntities.size()]; for (int i =
	 * 0; i < currentEntities.size(); i++) { if (currentEntities.get(i) != null)
	 * { strEntities[i] = ""; strEntities[i] +=
	 * currentEntities.get(i).toString(); } } return strEntities; }
	 */

}
