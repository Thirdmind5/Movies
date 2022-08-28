package com.openclassrooms.movies;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {

    private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
    private static  int index = 1;

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id){

        String viewName = "watchlistItemForm";
        Map<String,Object> model = new HashMap<String,Object>();

        WatchlistItem watchlistItem = findWatchlistItemById(id);

        if(watchlistItem == null){
            model.put("watchlistItem", new WatchlistItem());
        } else{
            model.put("watchlistItem", watchlistItem);
        }

        return  new ModelAndView(viewName,model);
    }

    private WatchlistItem findWatchlistItemById(Integer id) {

        for (WatchlistItem watchlistItem : watchlistItems){
            if (watchlistItem.getid().equals(id)){
                return watchlistItem;
            }
        }
        return null;
    }


    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem,
                                                BindingResult bindingResult){

       if(bindingResult.hasErrors()){
          return new ModelAndView("watchlistITemForm");
          }


        WatchlistItem watchlistItem1 = findWatchlistItemById(watchlistItem.getid());

        if (watchlistItem1 == null){
            watchlistItem.setid(index++);
            System.out.println(index);
            watchlistItems.add(watchlistItem);
        }else{
            watchlistItem1.setComment(watchlistItem.getComment());
            watchlistItem1.setTitle(watchlistItem.getTitle());
            watchlistItem1.setPriority(watchlistItem.getPriority());
            watchlistItem1.setRating(watchlistItem.getRating());
        }

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/watchlist");

        return new ModelAndView(redirect);

    }
    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(){

        String viewName = "watchlist";

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("watchlistItems",watchlistItems);
        model.put("numberOfMovies",watchlistItems.size());

        return new ModelAndView(viewName, model);
    }


}
