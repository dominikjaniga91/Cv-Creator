package com.cvgenerator.config;

import com.cvgenerator.utils.PhoneNumberAreaCodeProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PhoneNumberAreaCodeProvider provider(@Value("${area.code.data}") String path){
        return new PhoneNumberAreaCodeProvider(path);
    }
}
