package main.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.dao.ResourceDao;
import main.java.model.LinkResource;

@Service("resourceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LinkResourceService implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public void addResource(LinkResource l) {
		// TODO Auto-generated method stub
		resourceDao.addResource(l);
	}

}
