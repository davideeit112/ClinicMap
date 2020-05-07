package clinicMap.rss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	private ItemDAO idao;
	
	@Autowired
	public ItemService(ItemDAO idao) {
		this.idao = idao;
	}
	
	public List<Item> ItemList() throws Exception {
		return idao.ItemList();
	}
	
	
}
