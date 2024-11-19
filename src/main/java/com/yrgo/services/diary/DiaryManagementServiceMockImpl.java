package com.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yrgo.domain.Action;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DiaryManagementServiceMockImpl implements DiaryManagementService {
	
	private Set<Action>allActions= new HashSet<Action>();

	@Override
	public void recordAction(Action action) {
		allActions.add(action);
	}

	public List<Action> getAllIncompleteActions(String requiredUser) {
		List<Action> incompleteActions = new ArrayList<>();
		for (Action action : allActions) {
			if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
				incompleteActions.add(action);
			}
		}
		return incompleteActions;
	}

}
