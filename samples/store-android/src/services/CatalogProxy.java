package services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.json.rpc.JSONRpc;

public class CatalogProxy implements Catalog {
	private static final String jsonRPCServiceURI = "http://192.168.1.104:8080/Catalog";
    private static final String jsonRPCRequest = "{\"id\": 3, \"method\": \"Service.get\", \"params\": []}";
	
    private List<Item> catalog = new ArrayList<Item>();
    
    public CatalogProxy() {
    	initialize();
    }
   
	public void initialize() {
		JSONObject json = null; 
			
		try {
			json = JSONRpc.invoke(jsonRPCServiceURI, jsonRPCRequest);
			
			if(json == null) {
				return; 
			}
			
			JSONArray result = json.getJSONArray("result");
			for(int i = 0; i < result.length(); i++) {
				Item item = new Item();
				item.setName(result.getJSONObject(i).getString("name"));
				item.setPrice(result.getJSONObject(i).getString("price"));
				
				catalog.add(item);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Item[] get() {
		Item[] catalogArray = new Item[catalog.size()];
		catalog.toArray(catalogArray);
		return catalogArray;
	}
	
}
