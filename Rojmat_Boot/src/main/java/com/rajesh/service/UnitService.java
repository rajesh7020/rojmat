package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Unit;
import com.rajesh.repository.UnitRepository;

@Service
public class UnitService {
	
	@Autowired
	private UnitRepository unitRepository;
	public Unit saveOrUpdateCategory(Unit unit) throws RecordNotFoundException {
		Optional<Unit> u = unitRepository.findById(unit.getUnitid());
		if(u.isPresent()) {
			Unit newUnit = u.get();
			newUnit.setUnitname(unit.getUnitname());
			newUnit = unitRepository.save(newUnit);
			return newUnit;
		}else {
			unit = unitRepository.save(unit);
			return unit;
		}
	}
	public List<Unit> getAllUnits() {
		List<Unit> unitList = unitRepository.findAll();
		if(unitList.size() > 0) {
			return unitList;
		}else {
			return new ArrayList<Unit>();
		}
	}
	 public Unit getUnitById(Long unitId) throws RecordNotFoundException {
		 Optional<Unit> unit =  unitRepository.findById(unitId);
		 if(unit.isPresent()) {
			 return unit.get();
		 }else {
			 throw new RecordNotFoundException("No Unit record is exist for given id");
		 }
	 }
	 public void deleteUnit(Long unitId) throws RecordNotFoundException {
		 Optional<Unit> unit = unitRepository.findById(unitId);
		 if(unit.isPresent()) {
			 unitRepository.deleteById(unitId);
		 }else {
			 throw new RecordNotFoundException("No Unit record is exist for given id");
		 }
	 }
}
