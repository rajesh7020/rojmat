package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.State;
import com.rajesh.repository.StateRepository;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository;
	public State saveOrUpdateState(State state) throws RecordNotFoundException {
		Optional<State> s = stateRepository.findById(state.getStateid());
		if(s.isPresent()) {
			State newState = s.get();
			newState.setStatename(state.getStatename());
			newState = stateRepository.save(newState);
			return newState;
		}else {
			state = stateRepository.save(state);
			return state;
		}
	}
	public List<State> getAllStates() {
		List<State> stateList = stateRepository.findAll();
		if(stateList.size() > 0) {
			return stateList;
		}else {
			return new ArrayList<State>();
		}
	}
	 public State getStateById(Long stateId) throws RecordNotFoundException {
		 Optional<State> state =  stateRepository.findById(stateId);
		 if(state.isPresent()) {
			 return state.get();
		 }else {
			 throw new RecordNotFoundException("No state record is exist for given id");
		 }
	 }
	 public void deleteState(Long stateId) throws RecordNotFoundException {
		 Optional<State> state = stateRepository.findById(stateId);
		 if(state.isPresent()) {
			 stateRepository.deleteById(stateId);
		 }else {
			 throw new RecordNotFoundException("No state record is exist for given id");
		 }
	 }
}
