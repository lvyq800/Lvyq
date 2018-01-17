package test;

import java.util.ArrayList;

public class Repository {
    static ArrayList<Item> items = null;  //global values used by all users

    private ArrayList<Item> myItems = null;  //used to store puchase data for the specific user
    private int UserId = -1;  // used to identify a specific user



    public static void initItems(){  //initiate goods and values
        if(items == null) {
            items = new ArrayList<Item>();
            items.add(new Item("ItemA", 20));
            items.add(new Item("ItemB", 30));
            items.add(new Item("ItemC", 10));
        }
    }

    public void initMyAccount() {  //initiate purchase values for one specific user
        if(myItems == null) {
            myItems = new ArrayList<Item>();

            for (Item i : items) {
                myItems.add(new Item(i.getName(),0));
            }
        }
    }

    public ArrayList<Item> getAvailableItems(){
        return items;

    }

    public ArrayList<Item> summaryItems(){ // get purchase values for the specific user
        return myItems;

    }

    public void buyItems(ArrayList<Item> buyItems){  //buy goods, the global values will be updated accordingly as well as the user's private values
        for(int idx = 0; idx < items.size(); idx++){

            Item buyItem = buyItems.get(idx);
            Item myItem = myItems.get(idx);
            Item totalItem = items.get(idx);
            myItem.add(buyItem);
            totalItem.minus(buyItem);
            buyItem.setCount(totalItem.getCount());  //return store goods count number
            buyItem.setTmpCount(myItem.getCount());  //return count number of already owned goods by this user, these two variables are used to refresh the main page.
        }
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
