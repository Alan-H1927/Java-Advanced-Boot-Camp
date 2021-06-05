package com.study.autowired;

import com.study.bean.KclassInfo;
import com.study.config.SpringBootKclassConfiguration;
import com.study.prop.SpringBootKclassProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-05
 */
@Configuration
@Import(SpringBootKclassConfiguration.class)
@EnableConfigurationProperties(SpringBootKclassProperties.class)
public class SpringBootKclassAutowired {
    @Autowired
    SpringBootKclassProperties springBootKclassProperties;

    @Autowired
    SpringBootKclassConfiguration springBootKclassConfiguration;

    @Bean
    public KclassInfo createKclassInfo() {
        return new KclassInfo(springBootKclassConfiguration.name + "--->"
                + "，id--->" + springBootKclassProperties.getId()
                + "，name--->" + springBootKclassProperties.getName());
    }
}
