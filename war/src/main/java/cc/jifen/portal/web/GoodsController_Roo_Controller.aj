// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cc.jifen.portal.web;

import cc.jifen.portal.Goods;
import cc.jifen.portal.web.GoodsController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect GoodsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String GoodsController.create(@Valid Goods goods, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, goods);
            return "goodses/create";
        }
        uiModel.asMap().clear();
        goods.persist();
        return "redirect:/goodses/" + encodeUrlPathSegment(goods.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String GoodsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Goods());
        return "goodses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String GoodsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("goods", Goods.findGoods(id));
        uiModel.addAttribute("itemId", id);
        return "goodses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String GoodsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("goodses", Goods.findGoodsEntries(firstResult, sizeNo));
            float nrOfPages = (float) Goods.countGoodses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("goodses", Goods.findAllGoodses());
        }
        return "goodses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String GoodsController.update(@Valid Goods goods, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, goods);
            return "goodses/update";
        }
        uiModel.asMap().clear();
        goods.merge();
        return "redirect:/goodses/" + encodeUrlPathSegment(goods.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String GoodsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Goods.findGoods(id));
        return "goodses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String GoodsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Goods goods = Goods.findGoods(id);
        goods.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/goodses";
    }
    
    void GoodsController.populateEditForm(Model uiModel, Goods goods) {
        uiModel.addAttribute("goods", goods);
    }
    
    String GoodsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
