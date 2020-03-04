package config;

import org.aeonbits.owner.Config;

public interface Lab4Config extends Config {
    String browser();
    String hostname();
    long waittimeout();
}
