package cc.jifen.portal.web;

import cc.jifen.portal.GoodsCategory;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/goodscategorys")
@Controller
@RooWebScaffold(path = "goodscategorys", formBackingObject = GoodsCategory.class)
public class GoodsCategoryController {
}
