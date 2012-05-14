package eu.zomtec.em2012.web;

import eu.zomtec.em2012.domain.TeamGroup;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/teamgroups")
@Controller
@RooWebScaffold(path = "teamgroups", formBackingObject = TeamGroup.class)
public class TeamGroupController {
}
