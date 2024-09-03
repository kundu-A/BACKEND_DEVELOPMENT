package com.arpankundu.WatchListApplication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arpankundu.WatchListApplication.models.Movie;
import com.arpankundu.WatchListApplication.services.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {

	@Autowired
	DatabaseService databaseService;
	
	@GetMapping("/watchListItemForm")
	public ModelAndView showWatchListForm(@RequestParam(required=false) Integer id) {
		String viewName="WatchlistitemForm";
		
		Map<String,Object> model = new HashMap<>();
		if(id==null) {
			model.put("watchListItem", new Movie());
		}
		else {
			model.put("watchListItem", databaseService.getMovieById(id));
		}
		return new ModelAndView(viewName,model);
	}
	@PostMapping("/watchListItemForm")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchListItem") Movie movie , BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("WatchlistitemForm");
		}
		
		if(movie.getId()==null) {
			databaseService.create(movie);
		}
		else {
			databaseService.update(movie,movie.getId());
		}
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchList");
		
		return new ModelAndView(rd);
	}
	@GetMapping("/watchList")
	public ModelAndView getWatchList() {
		String viewName="Watchlist";
		Map<String , Object> model=new HashMap<>();
		model.put("noOfRows", databaseService.getMovies().size());
		model.put("watchListRows", databaseService.getMovies());
		
		return new ModelAndView(viewName,model);
	}
	@PostMapping("/watchList")
	public ModelAndView deleteWatchList(@RequestParam("id") Integer id ) {
		databaseService.delete(id);
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchList");
		return new ModelAndView(rd);
	}
}
