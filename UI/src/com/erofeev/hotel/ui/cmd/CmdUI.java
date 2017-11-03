package com.erofeev.hotel.ui.cmd;


import com.erofeev.hotel.ui.api.AbstractController;

import com.erofeev.hotel.ui.api.AbstractUI;
import com.erofeev.hotel.ui.api.AbstractView;

public class CmdUI implements AbstractUI{
	
	
private static volatile CmdUI instance;

private CmdUI(){}
	
	public static CmdUI getInstance() {
		CmdUI localInstance = instance;
		if (localInstance == null) {
			synchronized (CmdUI.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CmdUI();					
				}
			}
		}
		return localInstance;
	}
	
	

	@Override
	public AbstractView createView() {		
		return  CmdView.getInstance();
	}

	@Override
	public AbstractController createController() {		
		return CmdController.getInstance();
	}

}
