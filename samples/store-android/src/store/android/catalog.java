package store.android;

import java.util.ArrayList;
import java.util.List;

import services.Catalog;
import services.CatalogProxy;
import services.Item;
import services.ShoppingCartProxy;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class catalog  extends ListActivity {
	private Catalog catalogProxy = new CatalogProxy();
	private ShoppingCartProxy shoppingCartProxy = new ShoppingCartProxy(); 
		
	private String[] getCatalogItems() {
		List<String> catalog = new ArrayList<String>();
		
        for(Item item : catalogProxy.get()) {
        	catalog.add(item.getName() + " - " + item.getPrice());
        }	
        
        String[] catalogArray = new String[catalog.size()];
        catalog.toArray(catalogArray);
        
        return catalogArray;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, this.getCatalogItems()));

        Button addToCart = (Button) findViewById(1);
        /*
        addToCart.setOnClickListener(new View.OnClickListener() {
        	public void onCLick(View v) {
        		this.
        	}
        });
        */
        
        
        final ListView listView = getListView();

        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String item = this.getCatalogItems()[position];
		System.out.println("Item clicked : " + item);
		
		System.out.println("Selected items:");
		SparseBooleanArray checkedItems = l.getCheckedItemPositions();
		for(int i = 0; i < checkedItems.size(); i++) {
			if(checkedItems.valueAt(i) == true) {
				System.out.println(">>>" + this.getCatalogItems()[checkedItems.keyAt(i)]);
			}
		}
		System.out.println("Total: "  + shoppingCartProxy.getTotal());

		//l.getCheckedItemPosition();
		//Intent webIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://192.168.1.104:8080/ShoppingCart/Cart"));
		//startActivity(webIntent);
	} 
    
    
}