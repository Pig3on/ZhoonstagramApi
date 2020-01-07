package vua.pavic.ZhoonstagramApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vua.pavic.ZhoonstagramApi.utils.NullImage;
import vua.pavic.ZhoonstagramApi.utils.VisitableImage;

@Configuration
public class NullConfig {
    @Bean
    public VisitableImage visitableImage(){
        return new NullImage();
    }
}
