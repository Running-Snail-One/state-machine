//package com.demo.config;
//
//import com.fasterxml.classmate.TypeResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
///**
// * @description:
// * @author: 范帅兵
// * @create: 2020-11-25 23:55
// **/
//public class NSiteRestSwaggerConfiguration {
//    @Bean("_nSiteAPI_Docket_")
//    public Docket apiAuthc(TypeResolver typeResolver) {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("nSiteApi")
//                .additionalModels(typeResolver.resolve(DummyMap.class))
//                .apiInfo(new ApiInfo("nSite v3 API", "nSite融合媒体开发服务平台API", CommonProperties.getNSiteVersion(),
//                        "http://www.cdv.com", new Contact("新奥特（北京）视频技术有限公司", "http://www.cdv.com", ""),
//                        "CDV all rights reserved", "http://www.cdv.com", new ArrayList<>()))
//                .useDefaultResponseMessages(false).select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//
//                .paths(PathSelectors.ant("/api/nsite/**/*"))//
//                .paths((p) -> p != null && !(p.contains("/api/nsite/tgs") || p.contains("/api/nsite/im")))//
//                .build();
//    }
//}
