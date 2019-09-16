package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Gst;
import com.rajesh.repository.GstRepository;

@Service
public class GstService {
	@Autowired
	private GstRepository gstRepository;
	public Gst saveOrUpdateGst(Gst gst) throws RecordNotFoundException {
		Optional<Gst> g = gstRepository.findById(gst.getGstid());
		if(g.isPresent()) {
			Gst newGst = g.get();
			newGst.setPercentage(gst.getPercentage());
			newGst = gstRepository.save(newGst);
			return newGst;
		}else {
			gst = gstRepository.save(gst);
			return gst;
		}
	}
	public List<Gst> getAllGsts() {
		List<Gst> gstList = gstRepository.findAll();
		if(gstList.size() > 0) {
			return gstList;
		}else {
			return new ArrayList<Gst>();
		}
	}
	 public Gst getGstById(Long gstid) throws RecordNotFoundException {
		 Optional<Gst> gst =  gstRepository.findById(gstid);
		 if(gst.isPresent()) {
			 return gst.get();
		 }else {
			 throw new RecordNotFoundException("No Gst record is exist for given id");
		 }
	 }
	 public void deleteGst(Long gstid) throws RecordNotFoundException {
		 Optional<Gst> gst = gstRepository.findById(gstid);
		 if(gst.isPresent()) {
			 gstRepository.deleteById(gstid);
		 }else {
			 throw new RecordNotFoundException("No Gst record is exist for given id");
		 }
	 }
}
