package cc.jifen.portal.web;

import cc.jifen.portal.Goods;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/goodses")
@Controller
@RooWebScaffold(path = "goodses", formBackingObject = Goods.class)
public class GoodsController {
}
