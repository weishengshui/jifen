// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cc.jifen.portal.web;

import cc.jifen.portal.GoodsCategory;
import cc.jifen.portal.web.GoodsCategoryController;
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

privileged aspect GoodsCategoryController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String GoodsCategoryController.create(@Valid GoodsCategory goodsCategory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, goodsCategory);
            return "goodscategorys/create";
        }
        uiModel.asMap().clear();
        goodsCategory.persist();
        return "redirect:/goodscategorys/" + encodeUrlPathSegment(goodsCategory.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String GoodsCategoryController.createForm(Model uiModel) {
        populateEditForm(uiModel, new GoodsCategory());
        return "goodscategorys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String GoodsCategoryController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("goodscategory", GoodsCategory.findGoodsCategory(id));
        uiModel.addAttribute("itemId", id);
        return "goodscategorys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String GoodsCategoryController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("goodscategorys", GoodsCategory.findGoodsCategoryEntries(firstResult, sizeNo));
            float nrOfPages = (float) GoodsCategory.countGoodsCategorys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("goodscategorys", GoodsCategory.findAllGoodsCategorys());
        }
        return "goodscategorys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String GoodsCategoryController.update(@Valid GoodsCategory goodsCategory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, goodsCategory);
            return "goodscategorys/update";
        }
        uiModel.asMap().clear();
        goodsCategory.merge();
        return "redirect:/goodscategorys/" + encodeUrlPathSegment(goodsCategory.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String GoodsCategoryController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, GoodsCategory.findGoodsCategory(id));
        return "goodscategorys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String GoodsCategoryController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        GoodsCategory goodsCategory = GoodsCategory.findGoodsCategory(id);
        goodsCategory.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/goodscategorys";
    }
    
    void GoodsCategoryController.populateEditForm(Model uiModel, GoodsCategory goodsCategory) {
        uiModel.addAttribute("goodsCategory", goodsCategory);
    }
    
    String GoodsCategoryController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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

