// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cc.jifen.portal.web;

import cc.jifen.portal.Goods;
import cc.jifen.portal.GoodsCategory;
import cc.jifen.portal.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Goods, String> ApplicationConversionServiceFactoryBean.getGoodsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cc.jifen.portal.Goods, java.lang.String>() {
            public String convert(Goods goods) {
                return new StringBuilder().append(goods.getName()).append(' ').append(goods.getCode()).append(' ').append(goods.getModelNumber()).append(' ').append(goods.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, Goods> ApplicationConversionServiceFactoryBean.getIdToGoodsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cc.jifen.portal.Goods>() {
            public cc.jifen.portal.Goods convert(java.lang.Long id) {
                return Goods.findGoods(id);
            }
        };
    }
    
    public Converter<String, Goods> ApplicationConversionServiceFactoryBean.getStringToGoodsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cc.jifen.portal.Goods>() {
            public cc.jifen.portal.Goods convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Goods.class);
            }
        };
    }
    
    public Converter<GoodsCategory, String> ApplicationConversionServiceFactoryBean.getGoodsCategoryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cc.jifen.portal.GoodsCategory, java.lang.String>() {
            public String convert(GoodsCategory goodsCategory) {
                return new StringBuilder().append(goodsCategory.getName()).append(' ').append(goodsCategory.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, GoodsCategory> ApplicationConversionServiceFactoryBean.getIdToGoodsCategoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cc.jifen.portal.GoodsCategory>() {
            public cc.jifen.portal.GoodsCategory convert(java.lang.Long id) {
                return GoodsCategory.findGoodsCategory(id);
            }
        };
    }
    
    public Converter<String, GoodsCategory> ApplicationConversionServiceFactoryBean.getStringToGoodsCategoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cc.jifen.portal.GoodsCategory>() {
            public cc.jifen.portal.GoodsCategory convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GoodsCategory.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getGoodsToStringConverter());
        registry.addConverter(getIdToGoodsConverter());
        registry.addConverter(getStringToGoodsConverter());
        registry.addConverter(getGoodsCategoryToStringConverter());
        registry.addConverter(getIdToGoodsCategoryConverter());
        registry.addConverter(getStringToGoodsCategoryConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
