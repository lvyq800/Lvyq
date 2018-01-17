package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

@RestController
public class Controller {

    private static HttpHeaders responseHeaders = null;
    private ArrayList<Repository> repositories = new ArrayList<Repository>();

    private void initResponseHeader(){  //used to solve CORS problem
        if(responseHeaders == null){
            responseHeaders = new HttpHeaders();
            responseHeaders.set("Access-Control-Allow-Origin", "*");
            responseHeaders.set("Access-Control-Allow-Credentials", "true");
            responseHeaders.set("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
            responseHeaders.set("Access-Control-Max-Age", "3600");
            responseHeaders.set("Access-Control-Allow-Headers", "content-type, authorization");
        }

    }


    @RequestMapping(value = "/available", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody

    public ResponseEntity getAvailableItems() {  //get global values - counts for every goods
        Repository.initItems();
        initResponseHeader();
        ResponseEntity response = new ResponseEntity(Repository.items,responseHeaders, HttpStatus.OK);
        return response;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/buy", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity buyItems(@RequestBody String items) { //purchase a list of goods
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Item>>(){}.getType();
        ArrayList<Item> itms = gson.fromJson(items, collectionType);


        String str = items;
        int userId = itms.get(0).getCount();
        Repository repository = initMyAccount(userId);
        if(repository == null){
            return new ResponseEntity(null, HttpStatus.OK);
        }
        repository.buyItems(itms);
        ResponseEntity response = new ResponseEntity(itms, HttpStatus.OK);
        return response;


    }

    @RequestMapping(value = "/summary", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSummary(@RequestParam("user") String userId) {  //list all goods bought with count number
        Repository repository = initMyAccount(userId);
        if(repository == null){
            return new ResponseEntity(null, HttpStatus.OK);
        }
        ResponseEntity response = new ResponseEntity(repository.summaryItems(),responseHeaders, HttpStatus.OK);
        return response;

    }

    private Repository initMyAccount(String userId){ //creat/search repository for the specific user
        int uid = 0;
        try{
            uid = Integer.parseInt(userId);
        }catch (Exception e){
            return null;
        }
        if(uid <= 0){
            return null;
        }
        return initMyAccount(uid);
    }
    private Repository initMyAccount(int userId){
        for (Repository repo:repositories){
            if(repo.getUserId() == userId){
                return repo;
            }
        }
        Repository repo = new Repository();
        repo.setUserId(userId);
        repo.initMyAccount();
        repositories.add(repo);
        return repo;
    }

}