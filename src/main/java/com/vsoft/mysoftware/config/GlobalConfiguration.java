package com.vsoft.mysoftware.config;


import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.github.rozidan.springboot.modelmapper.ConfigurationConfigurer;


@Component
public class GlobalConfiguration extends ConfigurationConfigurer {
    
	@Override
    public void configure(Configuration configuration) {
        configuration.setSkipNullEnabled(true);
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
    }
}