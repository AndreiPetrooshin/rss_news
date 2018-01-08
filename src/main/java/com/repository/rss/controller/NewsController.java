package com.repository.rss.controller;

import com.repository.rss.domain.News;
import com.repository.rss.domain.RssHolder;
import com.repository.rss.domain.User;
import com.repository.rss.parser.exception.RssParserException;
import com.repository.rss.service.RssHolderService;
import com.repository.rss.service.impl.UserServiceImpl;
import com.repository.rss.service.manager.NewsManager;
import com.repository.rss.service.manager.RssHolderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes(value = {"rsses", "userForm", "allRss"})

public class NewsController {

    @Autowired
    private RssHolderService holderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NewsManager newsManager;

    @Autowired
    private RssHolderManager holderManager;

    @ModelAttribute("userForm")
    public User createUser() {
        return new User();
    }

    @ModelAttribute("rsses")
    public Set<RssHolder> createRssHolders() {
        return new HashSet<>();
    }

    @ModelAttribute("allRss")
    public List<RssHolder> createAllRss() {
        return new ArrayList<>();
    }


    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(@ModelAttribute("allRss") List<RssHolder> allRss,
                          Model model) {
        allRss = holderService.getAll();
        model.addAttribute("allRss", allRss);
        return "profile";
    }

    @RequestMapping(value = "news", method = RequestMethod.GET)
    public String newsList(@ModelAttribute("rsses") Set<RssHolder> rsses,
                           Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            User user = userService.findByLogin(name);
            rsses = user.getRssHolders();
            model.addAttribute("rsses", rsses);
            model.addAttribute("rss", new RssHolder());
        }
        return "news";
    }

    @RequestMapping("news/{id}/{category}")
    public String showNews(@PathVariable("id") int id,
                           @PathVariable("category") String category, Model model) {
        RssHolder holder = holderService.getById(id);
        try {
            List<News> newsList = newsManager.getListNews(Arrays.asList(holder));
            model.addAttribute("newses", newsList);
        } catch (RssParserException e) {
            return "error";
        }
        return "news";
    }

    @RequestMapping(value = "/add-rss", method = RequestMethod.POST)
    public String addRss(@ModelAttribute("rsses") Set<RssHolder> rsses,
                         @RequestParam String rssUrl,
                         Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/news";
        }
        String login = principal.getName();
        User user = userService.findByLogin(login);
        try {
            RssHolder rssHolder = holderManager.createRssHolder(rssUrl);
            Set<User> users = rssHolder.getUsers();
            rsses.add(rssHolder);
            users.add(user);
            holderService.save(rssHolder);
        } catch (RssParserException e) {
            model.addAttribute("error", "Не правильно задана ссылка, или такой ленты не существует");
        }
        return "news";
    }


    @RequestMapping(value = "/remove/{id}")
    public String removeRssHolderFromUser(@PathVariable int id, Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.findByLogin(name);
        RssHolder rssHolder = holderService.getById(id);
        rssHolder.getUsers().removeIf(user1 -> user1.getId() == user.getId());
        holderService.save(rssHolder);
        return "redirect:/news";
    }
}
