package eu.zomtec.em2012.web;

import eu.zomtec.em2012.domain.BetUser;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/betusers")
@Controller
@RooWebScaffold(path = "betusers", formBackingObject = BetUser.class)
public class BetUserController {
}
