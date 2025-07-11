package org.yisus.spring.ciclo_de_vida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExplicitBean {
    private static final Logger logger = LoggerFactory.getLogger(ExplicitBean.class);

    public void init(){
        logger.info("init ExplicitBean");
    }

    public void destroy(){
        logger.info("destroy ExplicitBean");
    }
}
