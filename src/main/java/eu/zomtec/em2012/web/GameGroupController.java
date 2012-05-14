package eu.zomtec.em2012.web;

import eu.zomtec.em2012.domain.GameGroup;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gamegroups")
@Controller
@RooWebScaffold(path = "gamegroups", formBackingObject = GameGroup.class)
public class GameGroupController {
}
