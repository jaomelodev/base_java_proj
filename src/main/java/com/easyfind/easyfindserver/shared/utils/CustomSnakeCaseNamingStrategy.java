package com.easyfind.easyfindserver.shared.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomSnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return new Identifier(addUnderscores(name.getText()), name.isQuoted());
    }

    protected static String addUnderscores(String name) {
        StringBuilder buf = new StringBuilder(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isUpperCase(buf.charAt(i)) && (Character.isLowerCase(buf.charAt(i - 1)) ||
                    (Character.isUpperCase(buf.charAt(i - 1)) && Character.isLowerCase(buf.charAt(i + 1))))) {
                buf.insert(i++, '_');
            }
        }
        String result = buf.toString();

        return result.toLowerCase();
    }
}
