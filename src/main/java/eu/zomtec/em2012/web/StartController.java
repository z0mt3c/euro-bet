package eu.zomtec.em2012.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Should contain the start page later - now just for testing.
 */
@RequestMapping("/start/**")
@Controller
public class StartController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public ModelAndView index(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("start/index", modelMap);
    }
}
